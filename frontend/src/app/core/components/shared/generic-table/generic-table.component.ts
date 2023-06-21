import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
  Injectable,
} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import {Subject} from 'rxjs';
import { $localize } from '@angular/localize/init';

@Injectable()
export class MyCustomPaginatorIntl implements MatPaginatorIntl {
  changes = new Subject<void>();

  // For internationalization, the `$localize` function from
  // the `@angular/localize` package can be used.
  firstPageLabel = $localize`First page`;
  itemsPerPageLabel = $localize`Items per page:`;
  lastPageLabel = $localize`Last page`;

  // You can set labels to an arbitrary string too, or dynamically compute
  // it through other third-party internationalization libraries.
  nextPageLabel = 'Next page';
  previousPageLabel = 'Previous page';

  getRangeLabel(page: number, pageSize: number, length: number): string {
    if (length === 0) {
      return $localize`Page 1 of 1`;
    }
    const amountPages = Math.ceil(length / pageSize);
    return $localize`Page ${page + 1} of ${amountPages}`;
  }
}

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  styleUrls: ['./generic-table.component.css'],
  providers: [{provide: MatPaginatorIntl, useClass: MyCustomPaginatorIntl}],
})
export class GenericTableComponent implements OnInit  {


  @Input() set preDataSource(data: any[]) {
    this.setUpDataInput(data);
  };
  @Input() set totalListDataMenu(data: any[]) {
    this.setUpDataMenuButton(data);
  }
  @Input() tableOptions: any;
  totalList: any[] = [];      // totale lista SKILLS esistenti
  dataSource: any;      // Dati che riempiranno la tabella
  roadmapSkillsDataSource: any;
  actionButtonValue: string = '';     // Stringa che viene usata come switchCase per il comportamento del BUTTON di aggiunta
  buttonTitle!: string;       //  Titolo del BUTTON
  buttonMenu: any[] = [];   //  Array che riempie la lista che fuoriesce dal BUTTON
  tmpData: any[] = [];    //  Array di appoggio per dati temporanei
  dataSourceMenuButton: any[] = [];   // Qui vengono mantenuti i dati dell'array che riempie il BUTTON per poi compararli con quelli presenti in tabella
  @Output() createNew = new EventEmitter<boolean>();
  @Output() viewDetails = new EventEmitter<any>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private router: Router){}

  ngOnInit(): void {
    this.setColumns();
    this.setActionButton();
    this.createNew.emit(false);
  }

  setUpDataInput(data: any[]): void {
    if(data.length > 0){
      if (this.tableOptions.type === 'roadmapSkills') {
        this.roadmapSkillsDataSource = this.returnData(data);
        data.forEach((parentSkill: any) => {
          this.tmpData.push(parentSkill.skill);
        });
        this.dataSource = new MatTableDataSource(this.tmpData);
      } else {
        this.dataSource = new MatTableDataSource(data);
      }
      this.dataSourceMenuButton = this.returnData(this.tmpData);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }
  }

  // Riempie "this.totalList" con tutti i dati passati da tenere come base per comparazioni a posteriori
  setUpDataMenuButton(data: any): void {
    // data.push({id: 100, title: 'Provaprova', description: 'Se funziona è sicuramente una bella cosa', enabled: true})
    this.totalList = data;
    this.compareListButtonMenu();
  }

  // Compara la lista di dati presenti nella tabella con quelli totali
  //  per aggiornare i dati presenti nel BUTTON
  compareListButtonMenu(): void {
    const data = this.totalList.filter((obj1: any) => {
      return !this.dataSourceMenuButton.some((obj2: any) => {
        return obj1.id === obj2.id;
      })
    })
    this.buttonMenu = data;
    if(this.buttonMenu.length === 0){
      this.buttonMenu.push({title: 'Nessun elemento da aggiungere'})
    }
  }

  // Ritorna la lista completa di dati passati dal componente padre
  getTotalList(): any[] {
    return this.totalList;
  }

  // Ritorna dati passatigli, usato per non far puntare due oggetti uguali
  // alla stessa parte di memoria
  returnData(data: any[]): any[] {
    return data;
  }

  // Aggiunge la colonna visualizza nella tabella
  setColumns(): void {
    this.tableOptions.tableDef.push({
      key: 'visualizza',
      header: 'Visualizza',
    });
    this.tableOptions.displayedColumns.push('visualizza');
    this.displayDynamicColumns();
  }

  // Fa un check se aggiungere le colonne elimina modifica e visualizza graficamente nella tabella
  displayDynamicColumns(): void {
    if (this.tableOptions.type === 'roadmap') {
      this.tableOptions.tableDef.push({
        key: 'visualizzazione_grafica',
        header: 'Visualizzazione Grafica',
      });
      this.tableOptions.displayedColumns.push('visualizzazione_grafica');
    }
    if (this.tableOptions.canModify) {
      this.tableOptions.tableDef.push({
        key: 'modifica',
        header: 'Modifica',
      });
      this.tableOptions.displayedColumns.push('modifica');
    }
    if (this.tableOptions.canDelete) {
      this.tableOptions.tableDef.push({
        key: 'cancella',
        header: 'Cancella',
      });
      this.tableOptions.displayedColumns.push('cancella');
    }
  }

  // Funzione per la ricerca all'interno dei campi della tabella
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  // Funzione al click del tasto visualizza generico
  visualizeRow(element: any){
    this.viewDetails.emit(element);
    console.log(this.buttonMenu)
    console.log(this.actionButtonValue)
  }

  // Funzione al click del tasto modifica
  modifyRow(element: object){
    const queryParams = {
      item: JSON.stringify(element),
      type: this.tableOptions.type,
      createMode: false
    }
    this.router.navigate(
      ['/form'],
      {
        queryParams: queryParams,
        queryParamsHandling: "merge"
    }
  );
  }

  // Funzione al click del tasto elimina
  deleteRow(elementId: number){
    console.log('Elemento: id ' + elementId + ' - eliminato');
  }

  // Funzione al click del tasto visualizza graficamente
  graphicVisualizeRow(element: object): void {
    console.log(element);
  }

  // Per la tabella Roadmap, reindirizza alla pagina di visualizzazione della roadmap
  roadmapViewer(element: number): void {
    this.router.navigate(['roadmap'], { state: { options: element } })
  }


  setActionButton(){
    if(this.tableOptions.btnCreate.canCreate && this.tableOptions.btnCreate.canView){
      if(this.tableOptions.type === 'roadmapSkills'){
        this.actionButtonValue = 'menuButton';
        this.buttonTitle = 'Associa Skill';
      } else {
        this.actionButtonValue = 'admin';
      }
    }
    if(!this.tableOptions.btnCreate.canCreate && this.tableOptions.btnCreate.canView && this.tableOptions.type !== 'mentee'){
      this.actionButtonValue = 'menuButton';
      console.log('sei sulla strada giusta')
      this.buttonTitle = 'Associa Skill';
      this.buttonMenu = [
        {title: 'Radmap 1'},
        {title: 'Radmap 2'},
        {title: 'Radmap 3'},
        {title: 'Radmap 4'},

      ]
    }
    if(!this.tableOptions.btnCreate.canCreate && this.tableOptions.btnCreate.canView && this.tableOptions.type === 'mentee'){
      this.actionButtonValue = 'mentee';
    }
    console.log(this.actionButtonValue)
  }

  createRoadmap(){
    const queryParams = {
      type: this.tableOptions.type,
      createMode: true
    }
    this.router.navigate(
      ['/form'],
      {
        queryParams: queryParams,
        queryParamsHandling: "merge"
    });
  }

  selectionItemButtonMenu(item: string){
    console.log(item);
  }
}
