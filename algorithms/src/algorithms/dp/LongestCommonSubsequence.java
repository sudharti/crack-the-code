package algorithms.dp;

public class LongestCommonSubsequence {

  public int lcs(String s1, String s2) {
    return lcs(s1, s2, s1.length(), s2.length());
  }

  private int lcs(String s1, String s2, int i, int j) {
    if (i == 0 || j == 0) {
      return 0;
    } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
      return lcs(s1, s2, i - 1, j - 1) + 1;
    } else {
      return Math.max(lcs(s1, s2, i, j - 1), lcs(s1, s2, i - 1, j));
    }
  }

  public int lcsTabulated(String s1, String s2) {
    int[][] table = new int[s1.length() + 1][s2.length() + 1];

    for (int i = 1; i < table.length; i++) {
      for (int j = 1; j < table[0].length; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          table[i][j] = table[i - 1][j - 1] + 1;
        } else {
          table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
        }
      }
    }

    return table[s1.length()][s2.length()];
  }

  public int lcsMemoized(String s1, String s2) {
    return lcsMemoized(s1, s2, s1.length(), s2.length(), new int[s1.length()][s2.length()]);
  }

  private int lcsMemoized(String s1, String s2, int i, int j, int[][] results) {
    if (i == 0 || j == 0) {
      return 0;
    }

    if (results[i - 1][j - 1] == 0) {
      if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
        results[i - 1][j - 1] = lcsMemoized(s1, s2, i - 1, j - 1, results) + 1;
      } else {
        results[i - 1][j - 1] = Math.max(lcsMemoized(s1, s2, i, j - 1, results),
            lcsMemoized(s1, s2, i - 1, j, results));
      }
    }
    return results[i - 1][j - 1];
  }

  public static void main(String[] args) {
    LongestCommonSubsequence lcs = new LongestCommonSubsequence();
    System.out.println(lcs.lcsTabulated("AerodroneAerodroneAerodrone", "AirpodAiAirpodrpod"));
    //System.out.println(lcs.lcsTabulated("AGGRTAB", "GXTXAYB"));
    System.out.println(lcs.lcsMemoized("AerodroneAerodroneAerodrone", "AirpodAiAirpodrpod"));
  }
}
