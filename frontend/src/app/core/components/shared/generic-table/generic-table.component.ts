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

//////////////////////////////////////////////////
/////                                        /////
/////   ----------------------------------   /////
/////   -------- INIZIO COMPONENT --------   /////
/////   ----------------------------------   /////
/////                                        /////
//////////////////////////////////////////////////

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  styleUrls: ['./generic-table.component.css'],
  providers: [{provide: MatPaginatorIntl, useClass: MyCustomPaginatorIntl}],
})
export class GenericTableComponent implements OnInit  {
  btnVisualizeQP!: { id: any; };


  @Input() set preDataSource(data: any[]) {
    this.setUpDataInput(data);
  };
  @Input() set totalListDataMenu(data: any[]) {
    this.setUpDataMenuButton(data);
  }
  @Input() tableOptions: any;
  @Output() createNew = new EventEmitter<boolean>();
  @Output() viewDetails = new EventEmitter<any>();
  @Output() updateData = new EventEmitter<any>();
  @Output() viewGraph = new EventEmitter<any>();
  @Output() viewGraphIdType = new EventEmitter<any>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private router: Router) {}


  totalList: any[] = [];      // totale lista SKILLS esistenti
  dataSource: any;      // Dati che riempiranno la tabella
  actionButtonValue: string = '';     // Stringa che viene usata come switchCase per il comportamento del BUTTON di aggiunta
  buttonTitle!: string;       //  Titolo del BUTTON
  buttonMenu: any[] = [];   //  Array che riempie la lista che fuoriesce dal BUTTON
  tmpData: any[] = [];    //  Array di appoggio per dati temporanei
  dataSourceMenuButton: any[] = [];   // Qui vengono mantenuti i dati dell'array che riempie il BUTTON per poi compararli con quelli presenti in tabella


  ngOnInit(): void {
    this.setColumns();
    this.setActionButton();
    this.createNew.emit(false);
  }


  public setUpDataInput(data: any[]): void {
    if(data.length > 0){
      this.tmpData = data;
      this.dataSource = new MatTableDataSource(data);
      this.dataSourceMenuButton = this.tmpData;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }
  }

  // Riempie "this.totalList" con tutti i dati passati da tenere come base per comparazioni a posteriori
  public setUpDataMenuButton(data: any): void {
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

  visualizeRow(element: any) {
    this.viewDetails.emit(element);
  }

  setActionButton() {
    if (
      this.tableOptions.btnCreate.canCreate &&
      this.tableOptions.btnCreate.canView
    ) {
      this.actionButtonValue = 'admin';
    }
    if (
      !this.tableOptions.btnCreate.canCreate &&
      this.tableOptions.btnCreate.canView
    ) {
      this.actionButtonValue = 'mentor';
      this.buttonMenu = ['Radmap 1', 'Radmap 2', 'Radmap 3', 'Radmap 4'];
    }
    if (
      !this.tableOptions.btnCreate.canCreate &&
      !this.tableOptions.btnCreate.canView
    ) {
      this.actionButtonValue = 'mentee';
    }
    console.log(this.actionButtonValue);
  }

  // Funzione al click del tasto modifica
  modifyRow(element: object){
    const queryParams = {
      item: JSON.stringify(element),
      type: this.tableOptions.type,
      createMode: false
    }
    this.router.navigate(['/form'], {
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
    this.viewGraph.emit(element);
  }

  // Per la tabella Roadmap, reindirizza alla pagina di visualizzazione della roadmap
  roadmapViewer(elementId: number, elementTitle: string): void {
    this.router.navigate(['roadmap'], { state: { options: {elementId, elementTitle} } })
  }


  addObject() {
    const queryParams = {
      type: this.tableOptions.type,
      createMode: true,
    };
    this.router.navigate(['/form'], {
      queryParams: queryParams,
      queryParamsHandling: 'merge',
    });
  }

  selectionItemButtonMenu(item: string) {
    this.updateData.emit(item);
  }


  view(id: any) {
    console.log(id)
    this.viewGraphIdType.emit({"id":id})
  }
}
