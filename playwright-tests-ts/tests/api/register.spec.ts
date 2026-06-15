import { test, expect } from '../../src/fixtures/api-fixtures';
import { ConfigManager } from '../../src/utils/config-manager';

test('API: should register a new user', async ({ api }) => {
  const userName = ConfigManager.getRandomUserName();
  const password = ConfigManager.getRandomPassword();

  const result = await api.post('/Account/v1/User', {
	userName,
	password,
	firstname: ConfigManager.getFirstName(),
	lastname: ConfigManager.getLastName(),
  });

  // Expect a successful 2xx response
  expect(result.status).toBeGreaterThanOrEqual(200);
  expect(result.status).toBeLessThan(300);
});


