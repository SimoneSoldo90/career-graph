import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  styleUrls: ['./generic-table.component.css'],
})
export class GenericTableComponent implements OnInit {
  @Input() set preDataSource(data: any[]) {
    this.setUpDataInput(data);
  }
  @Input() tableOptions: any;
  dataSource: any;
  actionButtonValue: string = '';
  buttonMenu: string[] = [];
  @Output() createNewRoadmap = new EventEmitter<boolean>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private router: Router){}

  ngOnInit(): void {
    this.setColumns();
    this.setActionButton();
    this.createNewRoadmap.emit(false);
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
    console.log(element)
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
    this.createNewRoadmap.emit(true);
  }

  selectionItemButtonMenu(item: string){
    console.log(item);
  }
}
