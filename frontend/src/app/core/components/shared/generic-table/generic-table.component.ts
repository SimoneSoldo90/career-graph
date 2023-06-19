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
import { localize } from '@angular/localize';

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
  }
  @Input() tableOptions: any;
  dataSource: any;
  actionButtonValue: string = '';
  buttonMenu: string[] = [];
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
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }
  }

  setColumns(): void {
    this.tableOptions.displayedColumns.push('visualizza');
    this.displayDynamicColumns();
  }

  displayDynamicColumns(): void {
    if (this.tableOptions.canModify) {
      this.tableOptions.displayedColumns.push('modify');
    }
    if (this.tableOptions.canDelete) {
      this.tableOptions.displayedColumns.push('delete');
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  visualizeRow(element: any){
    this.viewDetails.emit(element);
  }

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

  deleteRow(elementId: number){
    console.log('Elemento: id ' + elementId + ' - eliminato')
  }

  setActionButton(){
    if(this.tableOptions.btnCreate.canCreate && this.tableOptions.btnCreate.canView){
      this.actionButtonValue = 'admin';
    }
    if(!this.tableOptions.btnCreate.canCreate && this.tableOptions.btnCreate.canView){
      this.actionButtonValue = 'mentor';
      this.buttonMenu = [
        'Radmap 1',
        'Radmap 2',
        'Radmap 3',
        'Radmap 4',
      ]
    }
    if(!this.tableOptions.btnCreate.canCreate && !this.tableOptions.btnCreate.canView){
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
