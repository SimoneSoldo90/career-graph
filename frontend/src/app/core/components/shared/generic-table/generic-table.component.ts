import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  styleUrls: ['./generic-table.component.css']
})
export class GenericTableComponent implements OnInit {

  @Input() dataSource: any;
  // @Input() displayedColumns: string[] = [];
  @Input() tableOptions: any;


  ngOnInit(): void {
    this.setColumns();
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

}
