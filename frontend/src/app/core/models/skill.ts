import { Resource } from "./resource";

export interface Skill {

  "parentSkill"?: Skill;
  "id"?: number,
  "title": string,
  "description": string | null,
  "resources": Resource[],
  "enabled": boolean
  "parentSkillId":number
}
