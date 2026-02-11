 
import requests
from google.adk.agents import Agent
# --- STEP 1: Define the Tool (No Token Required) ---
def get_sonar_scan_results(pull_request_id: str) -> dict:
    """
    Fetches raw SonarCloud scan results for a specific Pull Request.
    This function performs an unauthenticated request for public projects.
    """
    # Using your specific componentKey
    project_key = "Samaresh-16_G_SAP_Squad"
    url = f"https://sonarcloud.io/api/issues/search?componentKeys={project_key}&pullRequest={pull_request_id}"
    
    try:
        # Direct request without headers/auth
        response = requests.get(url)
        response.raise_for_status()
        
        # Returns the raw JSON payload directly
        return response.json()
    except Exception as e:
        return {"status": "error", "message": f"Failed to fetch data: {str(e)}"}
# --- STEP 2: Define the Agent ---
root_agent = Agent(
    name="sonar_public_agent",
    model="gemini-2.5-flash",
    description="Fetches public SonarCloud PR scan data.",
    instruction="""
        You are a data retrieval assistant for SonarCloud. 
        When the user mentions a Pull Request number:
        1. Call the 'get_sonar_scan_results' tool using that PR number.
        2. Provide the raw JSON output exactly as the tool returns it.
        3. If the tool returns an error, explain it to the user.
    """,
    tools=[get_sonar_scan_results]
)
 