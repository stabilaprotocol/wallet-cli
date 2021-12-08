package org.stabila.keystore;


import org.stabila.common.crypto.SignInterface;

public interface Credentials {
  SignInterface getPair();

  String getAddress();
}
