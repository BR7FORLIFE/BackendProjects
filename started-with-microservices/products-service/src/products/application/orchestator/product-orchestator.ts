import { Injectable } from '@nestjs/common';
import { ProductRepository } from '../../infra/repository/product.repository.js';
import type { Product } from '../../../common/types/product.type.js';

@Injectable()
export class ProductUseCaseImp {
  constructor(private productRepository: ProductRepository) {}

  async generateProduct(
    product: Product,
  ): Promise<{ userId: string; sku: string }> {
    const { userId, sku } = await this.productRepository.save(product);

    return {
      userId,
      sku,
    };
  }
}
