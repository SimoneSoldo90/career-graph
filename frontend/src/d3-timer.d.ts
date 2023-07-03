declare module 'd3-timer' {
  export function now(): number;

  export function timer(callback: (elapsed: number) => void, delay?: number, time?: number): Timer;

  export interface Timer {
    restart(callback: (elapsed: number) => void, delay?: number, time?: number): Timer;
    stop(): void;
  }
}
