package org.example.staffmanagementweb.HR.config;

import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {
    
    private static ApplicationConfig instance;
    private String applicationName;
    private String version;
    private boolean debugMode;
    private int maxLoginAttempts;
    private int sessionTimeoutMinutes;
    
    // Private constructor to prevent instantiation
    private ApplicationConfig() {
        // Initialize default values
        this.applicationName = "Staff Management System";
        this.version = "1.0.0";
        this.debugMode = false;
        this.maxLoginAttempts = 3;
        this.sessionTimeoutMinutes = 30;
    }
    
    // Static method to get the singleton instance
    public static synchronized ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }
    
    // Configuration getters and setters
    public String getApplicationName() {
        return applicationName;
    }
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public boolean isDebugMode() {
        return debugMode;
    }
    
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
    
    public int getMaxLoginAttempts() {
        return maxLoginAttempts;
    }
    
    public void setMaxLoginAttempts(int maxLoginAttempts) {
        this.maxLoginAttempts = maxLoginAttempts;
    }
    
    public int getSessionTimeoutMinutes() {
        return sessionTimeoutMinutes;
    }
    
    public void setSessionTimeoutMinutes(int sessionTimeoutMinutes) {
        this.sessionTimeoutMinutes = sessionTimeoutMinutes;
    }
    
    // Utility methods
    public void printConfiguration() {
        System.out.println("=== Application Configuration ===");
        System.out.println("Application Name: " + applicationName);
        System.out.println("Version: " + version);
        System.out.println("Debug Mode: " + debugMode);
        System.out.println("Max Login Attempts: " + maxLoginAttempts);
        System.out.println("Session Timeout: " + sessionTimeoutMinutes + " minutes");
        System.out.println("================================");
    }
    
    public void resetToDefaults() {
        this.applicationName = "Staff Management System";
        this.version = "1.0.0";
        this.debugMode = false;
        this.maxLoginAttempts = 3;
        this.sessionTimeoutMinutes = 30;
        System.out.println("Configuration reset to defaults");
    }
}
