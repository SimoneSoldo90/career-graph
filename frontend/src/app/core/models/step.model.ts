import { Resource } from "./resource.model";
import { Skill } from "./skill";

export interface Step {
  id: number;
  roadmap_id: number;
  order: number;
  title: string;
  description: string;
  resources: Resource[]; // Puoi specificare il tipo appropriato per gli oggetti delle risorse
  roadmap_links: any[]; // Puoi specificare il tipo appropriato per gli oggetti dei collegamenti alla roadmap
  skills: Skill[]; // Puoi specificare il tipo appropriato per gli oggetti delle competenze
  status?: string;
}
