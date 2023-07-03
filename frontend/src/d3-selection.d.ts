import { EnterElement, Selection } from 'd3-selection';

declare module 'd3-selection' {
  // Selection type
  export type selection<GElement extends BaseType, Datum, PElement extends BaseType, PDatum> = Selection<
    GElement,
    Datum,
    PElement,
    PDatum
  >;

  export type ElementBase = Element | EnterElement | Document | Window;

  // Other members...
}

