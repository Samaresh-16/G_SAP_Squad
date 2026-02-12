<?php
$DB_USER = "root"; // hardcoded
$DB_PASS = "secret123"; // hardcoded
$mysqli = mysqli_connect("localhost", $DB_USER, $DB_PASS, "test");

$name = $_GET['name'];
$code = $_GET['code'];

// SQL injection via concatenation
$query = "SELECT * FROM users WHERE name = '$name'";
$result = mysqli_query($mysqli, $query);

// Dangerous eval
eval($code);

echo "Hello " . $name; // Potential XSS
?>
