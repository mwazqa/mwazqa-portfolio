import { test, expect } from '../../src/fixtures/api-fixtures';
import { ConfigManager } from '../../src/utils/config-manager';

test('API: should login with valid credentials and return token', async ({ api }) => {
  const token = await api.login(ConfigManager.getUserName(), ConfigManager.getPassword());
  expect(token).toBeTruthy();
});


