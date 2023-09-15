import { Resource } from "./resource.model";

export interface Skill {
  id?: number;
  title: string;
  description: string;
  resources?: Resource[];
  status?: string;
}
