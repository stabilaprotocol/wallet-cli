/*
 * java-stabila is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * java-stabila is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.stabila.common.utils;

import com.google.protobuf.ByteString;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.stabila.common.crypto.ECKey;
import org.stabila.common.crypto.Sha256Sm3Hash;
import org.stabila.common.crypto.SignInterface;
import org.stabila.common.crypto.SignatureInterface;
import org.stabila.core.exception.CancelException;
import org.stabila.protos.Protocol.Transaction;
import org.stabila.protos.contract.AccountContract.AccountCreateContract;
import org.stabila.protos.contract.AccountContract.AccountPermissionUpdateContract;
import org.stabila.protos.contract.AssetIssueContractOuterClass.AssetIssueContract;
import org.stabila.protos.contract.AssetIssueContractOuterClass.ParticipateAssetIssueContract;
import org.stabila.protos.contract.AssetIssueContractOuterClass.TransferAssetContract;
import org.stabila.protos.contract.AssetIssueContractOuterClass.UncdAssetContract;
import org.stabila.protos.contract.AssetIssueContractOuterClass.UpdateAssetContract;
import org.stabila.protos.contract.BalanceContract.CdBalanceContract;
import org.stabila.protos.contract.BalanceContract.TransferContract;
import org.stabila.protos.contract.BalanceContract.UncdBalanceContract;
import org.stabila.protos.contract.BalanceContract.WithdrawBalanceContract;
import org.stabila.protos.contract.SmartContractOuterClass.CreateSmartContract;
import org.stabila.protos.contract.SmartContractOuterClass.TriggerSmartContract;
import org.stabila.protos.contract.VoteAssetContractOuterClass.VoteAssetContract;
import org.stabila.protos.contract.ExecutiveContract.VoteExecutiveContract;
import org.stabila.protos.contract.ExecutiveContract.ExecutiveCreateContract;

public class TransactionUtils {

  /**
   * Obtain a data bytes after removing the id and SHA-256(data)
   *
   * @param transaction {@link Transaction} transaction
   * @return byte[] the hash of the transaction's data bytes which have no id
   */
  public static byte[] getHash(Transaction transaction) {
    Transaction.Builder tmp = transaction.toBuilder();
    // tmp.clearId();

    return Sha256Sm3Hash.hash(tmp.build().toByteArray());
  }

  public static byte[] getOwner(Transaction.Contract contract) {
    ByteString owner;
    try {
      switch (contract.getType()) {
        case AccountCreateContract:
          owner =
              contract
                  .getParameter()
                  .unpack(AccountCreateContract.class).getOwnerAddress();
          break;
        case TransferContract:
          owner =
              contract
                  .getParameter()
                  .unpack(TransferContract.class)
                  .getOwnerAddress();
          break;
        case TransferAssetContract:
          owner =
              contract
                  .getParameter()
                  .unpack(TransferAssetContract.class)
                  .getOwnerAddress();
          break;
        case VoteAssetContract:
          owner =
              contract
                  .getParameter()
                  .unpack(VoteAssetContract.class)
                  .getOwnerAddress();
          break;
        case VoteExecutiveContract:
          owner =
              contract
                  .getParameter()
                  .unpack(VoteExecutiveContract.class)
                  .getOwnerAddress();
          break;
        case ExecutiveCreateContract:
          owner =
              contract
                  .getParameter()
                  .unpack(ExecutiveCreateContract.class).getOwnerAddress();
          break;
        case AssetIssueContract:
          owner =
              contract
                  .getParameter()
                  .unpack(AssetIssueContract.class)
                  .getOwnerAddress();
          break;
        case ParticipateAssetIssueContract:
          owner =
              contract
                  .getParameter()
                  .unpack(ParticipateAssetIssueContract.class)
                  .getOwnerAddress();
          break;
        case CreateSmartContract:
          owner =
              contract
                  .getParameter()
                  .unpack(CreateSmartContract.class)
                  .getOwnerAddress();
          break;
        case TriggerSmartContract:
          owner =
              contract
                  .getParameter()
                  .unpack(TriggerSmartContract.class)
                  .getOwnerAddress();
          break;
        case CdBalanceContract:
          owner =
              contract
                  .getParameter()
                  .unpack(CdBalanceContract.class)
                  .getOwnerAddress();
          break;
        case UncdBalanceContract:
          owner =
              contract
                  .getParameter()
                  .unpack(UncdBalanceContract.class)
                  .getOwnerAddress();
          break;
        case UncdAssetContract:
          owner =
              contract
                  .getParameter()
                  .unpack(UncdAssetContract.class)
                  .getOwnerAddress();
          break;
        case WithdrawBalanceContract:
          owner =
              contract
                  .getParameter()
                  .unpack(WithdrawBalanceContract.class)
                  .getOwnerAddress();
          break;
        case UpdateAssetContract:
          owner =
              contract
                  .getParameter()
                  .unpack(UpdateAssetContract.class)
                  .getOwnerAddress();
          break;
        case AccountPermissionUpdateContract:
          owner =
              contract
                  .getParameter()
                  .unpack(AccountPermissionUpdateContract.class)
                  .getOwnerAddress();
          break;
        default:
          return null;
      }
      return owner.toByteArray();
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public static String getBase64FromByteString(ByteString sign) {
    byte[] r = sign.substring(0, 32).toByteArray();
    byte[] s = sign.substring(32, 64).toByteArray();
    byte v = sign.byteAt(64);
    if (v < 27) {
      v += 27; // revId -> v
    }
    ECKey.ECDSASignature signature = ECKey.ECDSASignature.fromComponents(r, s, v);
    return signature.toBase64();
  }

  /*
   * 1. check hash
   * 2. check double spent
   * 3. check sign
   * 4. check balance
   */
  public static boolean validTransaction(Transaction signedTransaction) {
    assert (signedTransaction.getSignatureCount()
        == signedTransaction.getRawData().getContractCount());
    List<Transaction.Contract> listContract = signedTransaction.getRawData().getContractList();
    byte[] hash = Sha256Sm3Hash.hash(signedTransaction.getRawData().toByteArray());
    int count = signedTransaction.getSignatureCount();
    if (count == 0) {
      return false;
    }
    for (int i = 0; i < count; ++i) {
      try {
        Transaction.Contract contract = listContract.get(i);
        byte[] owner = getOwner(contract);
        byte[] address =
            ECKey.signatureToAddress(
                hash, getBase64FromByteString(signedTransaction.getSignature(i)));
        if (!Arrays.equals(owner, address)) {
          return false;
        }
      } catch (SignatureException e) {
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }

  public static Transaction sign(Transaction transaction, SignInterface myKey) {
    Transaction.Builder transactionBuilderSigned = transaction.toBuilder();
    byte[] hash = Sha256Sm3Hash.hash(transaction.getRawData().toByteArray());
    SignatureInterface signature = myKey.sign(hash);
    ByteString bsSign = ByteString.copyFrom(signature.toByteArray());
    transactionBuilderSigned.addSignature(bsSign);
    transaction = transactionBuilderSigned.build();
    return transaction;
  }

  public static Transaction setTimestamp(Transaction transaction) {
    long currentTime = System.currentTimeMillis(); // *1000000 + System.nanoTime()%1000000;
    Transaction.Builder builder = transaction.toBuilder();
    org.stabila.protos.Protocol.Transaction.raw.Builder rowBuilder =
        transaction.getRawData().toBuilder();
    rowBuilder.setTimestamp(currentTime);
    builder.setRawData(rowBuilder.build());
    return builder.build();
  }

  public static Transaction setExpirationTime(Transaction transaction) {
    if (transaction.getSignatureCount() == 0) {
      long expirationTime = System.currentTimeMillis() + 6 * 60 * 60 * 1000;
      Transaction.Builder builder = transaction.toBuilder();
      org.stabila.protos.Protocol.Transaction.raw.Builder rowBuilder =
          transaction.getRawData().toBuilder();
      rowBuilder.setExpiration(expirationTime);
      builder.setRawData(rowBuilder.build());
      return builder.build();
    }
    return transaction;
  }

  public static Transaction setPermissionId(Transaction transaction, String tipString)
      throws CancelException {
    if (transaction.getSignatureCount() != 0
        || transaction.getRawData().getContract(0).getPermissionId() != 0) {
      return transaction;
    }

    System.out.println(tipString);
    int permission_id = inputPermissionId();
    if (permission_id < 0) {
      throw new CancelException("User cancelled");
    }
    if (permission_id != 0) {
      Transaction.raw.Builder raw = transaction.getRawData().toBuilder();
      Transaction.Contract.Builder contract =
          raw.getContract(0).toBuilder().setPermissionId(permission_id);
      raw.clearContract();
      raw.addContract(contract);
      transaction = transaction.toBuilder().setRawData(raw).build();
    }
    return transaction;
  }

  private static int inputPermissionId() {
    Scanner in = new Scanner(System.in);
    while (true) {
      String input = in.nextLine().trim();
      String str = input.split("\\s+")[0];
      if ("y".equalsIgnoreCase(str)) {
        return 0;
      }
      try {
        return Integer.parseInt(str);
      } catch (Exception e) {
        return -1;
      }
    }
  }
}
