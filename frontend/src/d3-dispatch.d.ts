declare module 'd3-dispatch' {
  interface Dispatch<T extends EventTarget> {
    call(type: string, ...args: any[]): void;
    apply(type: string, args: any[]): void;
    on(type: string): ((this: T, ...args: any[]) => void) | undefined;
    on(type: string, listener: (this: T, ...args: any[]) => void): Dispatch<T>;
    copy(): Dispatch<T>;
  }

  export function dispatch<T extends EventTarget>(): Dispatch<T>;
}
