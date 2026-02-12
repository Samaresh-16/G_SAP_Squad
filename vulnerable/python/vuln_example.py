import sqlite3
import hashlib

DB_USER = "admin"  # hardcoded credential
DB_PASS = "password123!"  # hardcoded credential


def insecure_login(username, password):
    # Weak crypto (MD5)
    hashed = hashlib.md5(password.encode()).hexdigest()

    # SQL injection via string concatenation
    conn = sqlite3.connect("test.db")
    cur = conn.cursor()
    query = "SELECT * FROM users WHERE name = '" + username + "' AND pass_hash = '" + hashed + "'"
    try:
        cur.execute(query)  # Non-parameterized query
        for row in cur.fetchall():
            print(row)
    except Exception:
        # Empty catch (code smell)
        pass
    finally:
        conn.close()


def bad_practices():
    temp = 42  # unused variable (code smell)
    code = input("Enter code to eval: ")
    eval(code)  # dangerous use of eval (security hotspot)
