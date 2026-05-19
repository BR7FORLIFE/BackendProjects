import type { registerUserDto } from '../dtos/register-user.dto';

export interface AuthUseCase {
  register(register: registerUserDto): void;
  initializerOAuth2(): { redirectUrl: string; state: string };
  handleOauth2Params(): { access_token: string };
}
