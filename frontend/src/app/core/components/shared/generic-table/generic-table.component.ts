import {
  Component,
  Input,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { CollectionViewer, DataSource } from '@angular/cdk/collections';
import { Observable, of, timeInterval } from 'rxjs';

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

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngOnInit(): void {
    this.setColumns();
  }

  setUpDataInput(data: any[]): void {
    if(data.length > 0){
      this.dataSource = new MatTableDataSource(data);
      console.log('dataSource');
      console.log(this.dataSource);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      console.log(this.tableOptions)
    }
  }

  displayDynamicColumns(): void {
    if (this.tableOptions.canModify) {
      this.tableOptions.displayedColumns.push('modify');
    }
    if (this.tableOptions.canDelete) {
      this.tableOptions.displayedColumns.push('delete');
    }
  }

  setColumns(): void {
    this.tableOptions.displayedColumns.push('visualizza');
    this.displayDynamicColumns();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
    console.log('applyFilter - dataInput');
    console.log(this.dataSource.filteredData);
  }
}
