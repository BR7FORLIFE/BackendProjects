import { Inject, Injectable } from '@nestjs/common';
import * as crypto from 'crypto';

import {
  OAUTH2_GOOGLE_PATH,
  OAUTH2_GOOGLE_CLIENT_ID,
  // OAUTH2_GOOGLE_CLIENT_SECRET,
  OAUTH2_GOOGLE_REDIRECT_URL,
} from '../../../constants';
import type { AuthUseCase } from '../usecases/auth.usecase';
import type { registerUserDto } from '../dtos/register-user.dto';
import type { IAuthRepositoryPort } from '../ports/auth-repository.port';

@Injectable()
export class AuthUseCaseImp implements AuthUseCase {
  constructor(@Inject('AUTH_REPOSITORY') repository: IAuthRepositoryPort) {}

  register(register: registerUserDto): void {}

  initializerOAuth2() {
    const state = crypto.randomBytes(20).toString('hex');

    const params = new URLSearchParams({
      client_id: OAUTH2_GOOGLE_CLIENT_ID!,
      redirect_uri: OAUTH2_GOOGLE_REDIRECT_URL!,
      response_type: 'code',
      scope: 'openid profile email',
      state,
      access_type: 'offline',
      prompt: 'consent',
    });

    const redirectUrl = `${OAUTH2_GOOGLE_PATH}?${params.toString()}`;
    // state para mandarlo en un cookie y ver el estado del auth
    //redirect url seria la url de oath2 google para poder autenticar al usuario
    return { redirectUrl, state };
  }

  handleOauth2Params(): { access_token: string } {
    return { access_token: '' };
  }
}
