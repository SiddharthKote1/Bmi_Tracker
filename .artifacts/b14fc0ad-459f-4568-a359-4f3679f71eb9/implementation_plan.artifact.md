# Implementation Plan - Login Screen

Implement the Login Screen UI based on the provided design image.

## Proposed Changes

### UI Components

#### [MODIFY] [LoginScreen.kt](file:///C:/Users/AsusTuf/AndroidStudioProjects/Bmi_Tracker/app/src/main/java/com/example/bmitrackerivinnovations/screens/LoginScreen.kt)
- Update the `LoginScreen` composable to include:
    - Back arrow icon button.
    - Title "Welcome Back" and subtitle "Login to continue".
    - "Continue with Google" button.
    - "or" separator with horizontal lines.
    - Email text field with person icon.
    - Password text field with lock icon and visibility toggle.
    - "Forgot Password?" clickable text.
    - Blue "Log In" button.
    - "Don't have an account? Sign Up" footer.

## Verification Plan

### Manual Verification
- Render the `LoginScreenPreview` to verify the UI matches the design image.
- Deploy the app and navigate to the Login screen to check interactions (text input, button clicks).
