import { Resource } from "./resource.model";
import { Skill } from "./skill";

export interface SkillResources extends Skill{
  resources: Resource[]
}
