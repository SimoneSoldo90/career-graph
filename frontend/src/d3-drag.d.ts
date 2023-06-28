declare module 'd3-drag' {
  import { Selection } from 'd3-selection';

  interface SubjectPosition {
    x: number;
    y: number;
  }

  type DragBehavior<GElement extends Element, Datum, Subject> = (
    | ((selection: Selection<GElement, Datum, any, any>) => void)
    | {
        container: (
          | ((this: GElement, datum: Datum, index: number, group: GElement[] | ArrayLike<GElement>) => Element | SVGSVGElement)
          | string
        );
        filter: (
          | ((this: GElement, datum: Datum, index: number, group: GElement[] | ArrayLike<GElement>) => boolean)
          | string
        );
        subject: (
          | ((this: GElement, datum: Datum, index: number, group: GElement[] | ArrayLike<GElement>) => Subject | undefined)
          | string
        );
        on: (
          | ((this: GElement, datum: Datum, index: number, group: GElement[] | ArrayLike<GElement>) => void)
          | string
        );
      }
  ) &
    Partial<{
      dispatch: (
        type: string,
        parameters: { x: number; y: number; dx: number; dy: number; subject: Subject; active: number },
        ...args: any[]
      ) => void;
      touchable: (
        this: GElement,
        datum: Datum,
        index: number,
        group: GElement[] | ArrayLike<GElement>
      ) => boolean;
    }>;

  export function drag<GElement extends Element, Datum, Subject>(): DragBehavior<GElement, Datum, Subject>;

  export const dragDisable: (selection: Selection<Element, any, any, any>) => void;

  export const dragEnable: (selection: Selection<Element, any, any, any>) => void;
}
