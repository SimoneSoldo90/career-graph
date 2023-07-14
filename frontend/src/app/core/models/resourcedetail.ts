import { Resource } from "./resource.model"

export interface Resourcedetail {
  "LINK"?: Resource[]

  "LINK_VIDEO"?:Resource[]

  "NOTE"?: Resource[]
}
export function getKeys(): any{
  return ["LINK", "LINK_VIDEO", "NOTE"];
}
