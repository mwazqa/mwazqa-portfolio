/**
 * Configuration manager for tests
 * Centralizes environment variables and test data
 */
export class ConfigManager {
  private static readonly BASE_URL = process.env.API_BASE_URL || 'https://demoqa.com';

  static getBaseUrl(): string {
    return this.BASE_URL;
  }

  static getUserName(): string {
    return 'ExampleUserName';
  }

  static getPassword(): string {
    return 'ExamplePassword@123';
  }

  static getFirstName(): string {
    return 'Example First Name';
  }

  static getLastName(): string {
    return 'Example Last Name';
  }

  static getRandomUserName(): string {
    return `user_${Math.random().toString(36).substring(7)}`;
  }

  static getRandomPassword(): string {
    // Generate random password with at least one uppercase, one digit
    return `Pass${Math.random().toString(36).substring(2, 8)}@123`;
  }
}

