export enum Color {
  bc_primary = '#144d83',
  done = '#00b894',
  todo = '#ededed',
  doing = '#ffeaa7',
}
export function getColorFromString(colorString: string | undefined): string {
  if (!colorString) {
    return 'white';
  }
  const lowercaseColorString = colorString.toLowerCase();
  const colorValue = Color[lowercaseColorString as keyof typeof Color];
  return colorValue || 'white';
}
