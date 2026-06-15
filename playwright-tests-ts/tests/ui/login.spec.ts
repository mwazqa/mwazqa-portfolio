import { test, expect } from '../../src/fixtures/config';
import { LoginPage } from '../../src/pages/login-page';
import { ConfigManager } from '../../src/utils/config-manager';

test('UI: should login with valid credentials', async ({ page }) => {
  const loginPage = new LoginPage(page);
  await loginPage.goToLoginPage();
  await loginPage.login(ConfigManager.getUserName(), ConfigManager.getPassword());

  // Wait for logout button or other indication of successful login
  await page.waitForSelector(loginPage.logoutButton, { timeout: 5000 }).catch(() => null);
  const loggedIn = await page.isVisible(loginPage.logoutButton).catch(() => false);
  expect(loggedIn).toBeTruthy();
});


