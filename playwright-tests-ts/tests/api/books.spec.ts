import { test, expect } from '../../src/fixtures/api-fixtures';
import { ConfigManager } from '../../src/utils/config-manager';

test('API: should register, login and add a book to the user collection', async ({ api }) => {
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
  const userId = (registerResult.body && (registerResult.body.userID || registerResult.body.userId)) as string;

  // login
  const token = await api.login(userName, password);
  expect(token).toBeTruthy();

  // add books
  const addResult = await api.addBooks(token, userId || userName, ['9781593275846']);
  expect(addResult.status).toBeGreaterThanOrEqual(200);
  expect(addResult.status).toBeLessThan(300);
});


