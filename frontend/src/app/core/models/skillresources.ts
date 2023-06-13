import { Resource } from "./resource";
import { Skill } from "./skill";

export interface SkillResources extends Skill{
  resources: Resource[]
}
