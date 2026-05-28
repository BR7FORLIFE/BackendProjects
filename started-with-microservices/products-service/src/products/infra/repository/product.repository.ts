import { Injectable } from '@nestjs/common';
import { PrismaClientConfig } from '../../../config/prisma-client.js';
import type { Product } from '../../../common/types/product.type.js';

@Injectable()
export class ProductRepository {
  constructor(private prismaClient: PrismaClientConfig) {}

  async save(product: Product): Promise<{ userId: string; sku: string }> {
    const saved = await this.prismaClient.products.create({
      data: product,
    });

    return {
      userId: saved.userId,
      sku: saved.sku,
    };
  }
}
