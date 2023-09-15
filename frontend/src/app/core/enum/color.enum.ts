export enum Color {
  bc_primary = '#144d83',
  done = '#05c46b',
  todo = '#ededed',
  doing = '#ffdd59',
}
export function getColorFromString(colorString: string | undefined): string {
  if (!colorString) {
    return 'white';
  }
  const lowercaseColorString = colorString.toLowerCase();
  const colorValue = Color[lowercaseColorString as keyof typeof Color];
  return colorValue || 'white';
}
