package com.pratice;

public class CompareVersions {

    public int compareVersion(String version1, String version2) {
       int i = 0, V1 = 0, j = 0, V2 = 0;

       while (true) {
           while (i < version1.length() && version1.charAt(i) != '.') {
               V1 = V1 * 10 + version1.charAt(i) - 48;
               i++;
           }
           while (j < version2.length() && version2.charAt(j) != '.') {
               V2 = V2 * 10 + version2.charAt(j) - 48;
               j++;
           }

           if (V1 > V2) return 1;
           else if (V1 < V2) return -1;
           if (i >= version1.length() && j >= version2.length()) return 0;

           i++;
           j++;
           V1 = V2 = 0;
       }
    }

    public static void main(String[] args) {
        CompareVersions cls = new CompareVersions();
        String version1 = "7.5.2.4";
        String version2 = "7.5.3";

        System.out.println(cls.compareVersion(version1, version2));
    }
}
