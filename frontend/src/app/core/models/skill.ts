import { Resource } from "./resource.model";

export interface Skill {
  id?: number;
  step_id: number;
  title: string;
  description: string;
  resources?: Resource[];
}
