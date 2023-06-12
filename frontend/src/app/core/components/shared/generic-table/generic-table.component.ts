import { Component, Input, OnInit, AfterContentChecked } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  styleUrls: ['./generic-table.component.css']
})
export class GenericTableComponent implements OnInit, AfterContentChecked {

  @Input() preDataSource: any;
  @Input() tableOptions: any;
  dataSource: any;
  tmpDataSource: any;

  ngOnInit(): void {
    this.setColumns();
  }

  // da implementare una view loading in caso di ritardo del caricamento dei dati
  ngAfterContentChecked(): void {
    const inputFilter = document.getElementById('inputFilter');
    const filterValue = (inputFilter as HTMLInputElement).value;
    if(filterValue === ''){
      this.dataSource = new MatTableDataSource(this.preDataSource);
    }
  }

  displayDynamicColumns(): void{
    if(this.tableOptions.canModify){
      this.tableOptions.displayedColumns.push("modify");
    }
    if(this.tableOptions.canDelete) {
      this.tableOptions.displayedColumns.push("delete")
    }
  }

  setColumns(): void{
    this.tableOptions.displayedColumns.push("visualizza");
    this.displayDynamicColumns();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
