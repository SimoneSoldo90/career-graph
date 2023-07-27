export interface TableOptions {
  type: string;
  displayedColumns: string[];
  tableDef: any; // Replace 'any' with the specific type for tableDef if possible
  canDelete: boolean;
  canModify: boolean;
  btnCreate?: {
    type: string;
    title: string;
    canCreate: boolean;
    canView: boolean;
  };
  idRoadmap?: number;
  title: string;
  detailTitle: string;
  emptyData: boolean;
  btnVisualize?: {
    canViewGraph:boolean;
    canView: boolean;
    tooltip: string;
    routerLink: string;
    queryParams: { id: number };
  };
  btnNavigate?:{
    canNavigate:boolean;
  };
}
