import { Injectable } from '@nestjs/common';
import { PrismaClient } from '../../generated/prisma/client.js';
import { PrismaPg } from '@prisma/adapter-pg';
import { POSTGRES_URI } from '../constants.js';

@Injectable()
export class PrismaConfig extends PrismaClient {
  constructor() {
    const adapter = new PrismaPg({
      connectionString: POSTGRES_URI as string,
    });

    super({ adapter });
  }
}
