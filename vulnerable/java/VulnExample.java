import java.security.MessageDigest;
import java.sql.*;
import java.util.*;

public class VulnExample {
    private static final String DB_USER = "root"; // hardcoded
    private static final String DB_PASS = "root123"; // hardcoded

    public static void main(String[] args) throws Exception {
        String userInput = "test' OR '1'='1"; // simulate tainted input
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", DB_USER, DB_PASS);

            // Weak crypto usage
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest("password".getBytes());

            // SQL injection via concatenated query
            String query = "SELECT * FROM users WHERE name = '" + userInput + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

            // Lame/inefficient method usage
            List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
            int lame = lameSum(nums);
            System.out.println("lameSum: " + lame);

            // Highly complex method usage
            int complex = overlyComplexProcessor("alpha", 12, true, Arrays.asList("x", "abc", "xyz", "", "right"));
            System.out.println("complex: " + complex);
        } catch (Exception e) {
            // Silent catch (code smell)
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (Exception ignored) {}
            }
        }
    }

    // Lame method: O(n^2) with string concatenation inside the loop
    static int lameSum(List<Integer> nums) {
        String trace = "";
        int total = 0;
        for (int i = 0; i < nums.size(); i++) {
            trace += nums.get(i); // String concatenation in loop (code smell)
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) {
                    total += nums.get(i);
                }
            }
        }
        return total;
    }

    // Highly complex method: deep nesting, many branches, and switches
    static int overlyComplexProcessor(String kind, int value, boolean flag, List<String> items) {
        int result = 0;
        if (kind == null) {
            if (flag) {
                if (value > 10) {
                    result += value;
                } else if (value == 10) {
                    result -= value;
                } else {
                    result *= 2;
                }
            } else {
                if (value < 0) {
                    result = -value;
                } else {
                    result = value;
                }
            }
        } else if ("alpha".equals(kind)) {
            switch (value % 5) {
                case 0: result += 1; break;
                case 1: result += 2; break;
                case 2: result += 3; break;
                case 3: result += 4; break;
                default: result += 5; break;
            }
            for (int i = 0; i < items.size(); i++) {
                String it = items.get(i);
                if (it.isEmpty()) {
                    continue;
                } else if (it.startsWith("x")) {
                    result += i;
                    if (flag) {
                        if (value % 2 == 0) {
                            result++;
                        } else {
                            result--;
                        }
                    } else {
                        if (value % 3 == 0) {
                            result += 3;
                        } else if (value % 3 == 1) {
                            result += 1;
                        } else {
                            result -= 1;
                        }
                    }
                } else if (it.length() > 5) {
                    result += it.length();
                } else {
                    result -= it.length();
                }
            }
        } else if ("beta".equals(kind)) {
            for (String s : items) {
                switch (s) {
                    case "up": result += 10; break;
                    case "down": result -= 10; break;
                    case "left": result += 5; break;
                    case "right": result -= 5; break;
                    default:
                        if (flag) {
                            if (value > 100) {
                                result += 100;
                            } else {
                                result += 50;
                            }
                        } else {
                            if (value < -100) {
                                result -= 100;
                            } else {
                                result -= 50;
                            }
                        }
                }
                if (result > 1000) {
                    break;
                }
            }
        } else {
            if (items == null) {
                return result;
            }
            for (int i = 0; i < items.size(); i++) {
                String s = items.get(i);
                if (s == null) {
                    continue;
                }
                if (s.contains("a") && s.contains("b") && s.contains("c")) {
                    result += i;
                } else if (s.contains("x") || s.contains("y") || s.contains("z")) {
                    result -= i;
                } else if (s.matches(".*\\d.*")) {
                    result += s.length();
                } else {
                    if (flag && value > 0) {
                        result += value;
                    } else if (!flag && value == 0) {
                        result -= value;
                    } else {
                        result = result;
                    }
                }
            }
        }
        return result;
    }
}
