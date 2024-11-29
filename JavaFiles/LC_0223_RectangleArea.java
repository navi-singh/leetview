public class LC223_RectangleArea {
  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    int totalArea = (C - A) * (D - B) + (G - E) * (H - F);
    if (C < E || G < A || D < F || H < B) {
      return totalArea;
    }
    int left = Math.max(A, E);
    int right = Math.min(C, G);
    int bottom = Math.max(B, F);
    int top = Math.min(D, H);
    return totalArea - (right - left) * (top - bottom);
  }
}
