// Hardcoded secret (credential)
const API_KEY = "sk_live_1234567890";

function buildQuery(name) {
  // SQL injection via concatenation
  return "SELECT * FROM users WHERE name = '" + name + "'";
}

function render(req) {
  const userCode = req.query.code;
  eval(userCode); // dangerous eval

  const name = req.query.name;
  const query = buildQuery(name);

  // Potential XSS sink
  document.getElementById("output").innerHTML = name;
  console.log("Query:", query);
}

try {
  render({ query: { name: "bob' OR '1'='1", code: "console.log('hi')" } });
} catch (e) {
  // empty catch
}
