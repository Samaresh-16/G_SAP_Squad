# G_SAP_Squad
Intentionally vulnerable samples to test Sonar detections.

## ⚠️ Warning
- This code is intentionally insecure. Do not deploy or reuse.
- Use only for static analysis testing in a safe environment.

## Contents
- [vulnerable/python/vuln_example.py](vulnerable/python/vuln_example.py)
- [vulnerable/java/VulnExample.java](vulnerable/java/VulnExample.java)
- [vulnerable/javascript/vuln-example.js](vulnerable/javascript/vuln-example.js)
- [vulnerable/csharp/VulnExample.cs](vulnerable/csharp/VulnExample.cs)
- [vulnerable/php/vuln_example.php](vulnerable/php/vuln_example.php)
- [sonar-project.properties](sonar-project.properties)

## What Sonar should flag
- Hardcoded credentials/secrets.
- SQL injection via string concatenation.
- Weak crypto (MD5).
- Dangerous `eval` usage.
- Empty catch blocks and unused variables (code smells).
- XSS sink via `innerHTML`/echo of tainted input.

## Quick scan (SonarScanner)
1. Install on macOS:
	```bash
	brew install sonar-scanner
	```
2. Run from repo root:
	```bash
	sonar-scanner
	```
3. For SonarQube locally (optional):
	- Start SonarQube (e.g., via Docker), then configure `sonar-scanner` to point to it.

## Notes
- Files are minimal and not meant to run; they exist to be analyzed.
- Adjust `sonar-project.properties` if using SonarCloud or a different server.
