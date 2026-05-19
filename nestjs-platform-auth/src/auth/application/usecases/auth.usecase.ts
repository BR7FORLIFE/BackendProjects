import type { registerUserDto } from '../dtos/register-user.dto.js';

export interface AuthUseCase {
  register(
    register: registerUserDto,
  ): Promise<{ id: string; username: string }>;
  initializerOAuth2(): { redirectUrl: string; state: string };
  handleOauth2Params(): { access_token: string };
}
