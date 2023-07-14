import { Step } from "./step.model";

export interface Roadmap {
  id?: number;
  title: string;
  description: string;
  steps?: Step[];
}
