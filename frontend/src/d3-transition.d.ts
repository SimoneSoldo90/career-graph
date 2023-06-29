declare module 'd3-transition' {
  import { BaseType, Selection } from 'd3-selection';

  export interface Transition<GElement extends BaseType, Datum = any, PElement extends BaseType = HTMLElement, PDatum = any> {
    select(selector: string): Transition<GElement, Datum, PElement, PDatum>;
    select(selector: (this: GElement, datum: Datum, index: number, groups: GElement[] | ArrayLike<GElement>) => BaseType): Transition<GElement, Datum, PElement, PDatum>;
    selectAll(selector: string): Transition<GElement, Datum, PElement, PDatum>;
    selectAll(selector: (this: GElement, datum: Datum, index: number, groups: GElement[] | ArrayLike<GElement>) => BaseType[] | ArrayLike<BaseType>): Transition<GElement, Datum, PElement, PDatum>;
    transition(): Transition<GElement, Datum, PElement, PDatum>;
    transition<NewGElement extends BaseType>(name: string): Transition<NewGElement, Datum, PElement, PDatum>;
    transition<NewGElement extends BaseType>(name: (this: GElement, datum: Datum, index: number, groups: GElement[] | ArrayLike<GElement>) => NewGElement): Transition<NewGElement, Datum, PElement, PDatum>;
    call(
      callback: (
        transition: Transition<GElement, Datum, PElement, PDatum>,
        ...args: any[]
      ) => void,
      ...args: any[]
    ): this;

    // Other transition methods...

    // Additional methods and properties...
  }

  export function transition<GElement extends BaseType, Datum = any, PElement extends BaseType = HTMLElement, PDatum = any>(
    selection: Selection<GElement, Datum, PElement, PDatum> | Transition<GElement, Datum, PElement, PDatum>
  ): Transition<GElement, Datum, PElement, PDatum>;
}
