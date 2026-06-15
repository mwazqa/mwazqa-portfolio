import { test, expect } from '../../src/fixtures/api-fixtures';
import { ConfigManager } from '../../src/utils/config-manager';

test('API: should register, login and fetch user profile', async ({ api }) => {
  const userName = ConfigManager.getRandomUserName();
  const password = ConfigManager.getRandomPassword();

  // register
  const registerResult = await api.post('/Account/v1/User', {
	userName,
	password,
	firstname: ConfigManager.getFirstName(),
	lastname: ConfigManager.getLastName(),
  });
  expect(registerResult.status).toBeGreaterThanOrEqual(200);
  expect(registerResult.status).toBeLessThan(300);

  // login
  const token = await api.login(userName, password);
  expect(token).toBeTruthy();

  // get profile
  const profileResult = await api.getUserProfile(token, userName);
  expect(profileResult.status).toBeGreaterThanOrEqual(200);
  expect(profileResult.status).toBeLessThan(300);
  const body = profileResult.body as any;
  expect(body.username || body.userName).toBe(userName);
});


