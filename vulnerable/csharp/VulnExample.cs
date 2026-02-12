using System;
using System.Data.SqlClient;
using System.Security.Cryptography;

class VulnExample {
    const string DB_USER = "sa"; // hardcoded
    const string DB_PASS = "P@ssw0rd"; // hardcoded

    static void Main() {
        Console.Write("Name: ");
        var input = Console.ReadLine();

        using (var conn = new SqlConnection("Server=localhost;Database=Test;User Id=" + DB_USER + ";Password=" + DB_PASS + ";")) {
            // Weak crypto
            var md5 = MD5.Create();
            var digest = md5.ComputeHash(System.Text.Encoding.UTF8.GetBytes("password"));

            // SQL injection via string concat
            var cmd = new SqlCommand("SELECT * FROM Users WHERE Name = '" + input + "'", conn);
            try {
                conn.Open();
                var reader = cmd.ExecuteReader();
                while (reader.Read()) {
                    Console.WriteLine(reader["Name"]);
                }
            } catch (Exception) {
                // empty catch
            }
        }
    }
}
