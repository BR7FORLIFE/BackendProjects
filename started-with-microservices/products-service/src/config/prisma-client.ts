import {
  Injectable,
  type OnModuleDestroy,
  type OnModuleInit,
} from '@nestjs/common';
import { PrismaClient } from '@prisma/client';

import { POSTGRES_URI } from '../../constants.js';
import { PrismaPg } from '@prisma/adapter-pg';

@Injectable()
export class PrismaClientConfig
  extends PrismaClient
  implements OnModuleInit, OnModuleDestroy
{
  constructor() {
    const adapter = new PrismaPg({
      connectionString: POSTGRES_URI!,
    });

    super({ adapter });
  }

  async onModuleInit() {
    await this.$connect();
  }

  async onModuleDestroy() {
    await this.$disconnect();
  }
}
