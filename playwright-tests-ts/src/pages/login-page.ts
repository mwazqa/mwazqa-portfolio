import { Page } from '@playwright/test';

/**
 * LoginPage - Page Object Model for login page
 * Encapsulates selectors and common actions related to login
 */
export class LoginPage {
  readonly page: Page;
  readonly usernameField = '#userName';
  readonly passwordField = '#password';
  readonly loginButton = '#login';
  readonly logoutButton = '#submit';

  constructor(page: Page) {
    this.page = page;
  }

  async goToLoginPage(): Promise<void> {
    await this.page.goto('https://demoqa.com/login');
  }

  async login(username: string, password: string): Promise<void> {
    await this.page.fill(this.usernameField, username);
    await this.page.fill(this.passwordField, password);
    await this.page.click(this.loginButton);
  }

  async logout(): Promise<void> {
    await this.page.click(this.logoutButton);
  }

  async getErrorMessage(): Promise<string | null> {
    return this.page.textContent('#name');
  }
}

