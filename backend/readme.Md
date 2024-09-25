Here’s how to set environment variables for GOOGLE_CLIENT_ID and GOOGLE_CLIENT_SECRET:

On Windows:
Open Command Prompt or PowerShell.
Run the following commands:
```bash
setx GOOGLE_CLIENT_ID "your-client-id"
setx GOOGLE_CLIENT_SECRET "your-client-secret"
```

On Linux/macOS:
Open a terminal.
Add these lines to your ~/.bashrc or ~/.zshrc file:
```bash
export GOOGLE_CLIENT_ID="your-client-id"
export GOOGLE_CLIENT_SECRET="your-client-secret"
```

Run 
```source ~/.bashrc 
```
or 
```
source ~/.zshrc
```
to apply the changes.
This will make the variables available for your application.






