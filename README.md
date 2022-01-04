<h1 align="center">
  <img align="center" src="https://raw.githubusercontent.com/stabilaprotocol/wiki/master/images/Logo_Stabila_WhiteMetal.png" width="200"/>
</h1>

# wallet-cli [![Build Status](https://travis-ci.org/stabilaprotocol/wallet-cli.svg?branch=master)](https://travis-ci.org/stabilaprotocol/wallet-cli)

Welcome to use the Wallet-CLI.  

## Get started

### Download wallet-cli

    git clone https://github.com/stabilaprotocol/wallet-cli.git

### Edit config.conf in src/main/resources

```
net {
  type = mainnet
}

fullnode = {
  ip.list = [
    "fullnode ip : port"
  ]
}

soliditynode = {
  // the IPs in this list can only be totally set to solidity or pBFT.
  ip.list = [
    "ip : solidity port" // default solidity
  ]
#  ip.list = [
#    "ip : pBFT port" // or pBFT
#  ]
} // NOTE: solidity node is optional

blockNumberStartToScan = 22690588 // NOTE: this field is optional
```

### Run a web wallet

- connect to fullNode

    Take a look at: [java-stabila deployment](https://github.com/stabilaprotocol/stabila-deployment)

    NOTE: The wallet-cli node would consume a lot of memory and CPU. Please be aware if you do not use wallet, just kill them.
- compile and run web wallet

    ```console
    $ cd wallet-cli
    $ ./gradlew build
    $ cd build/libs
    $ java -jar wallet-cli.jar
    ```

### Connect to java-stabila

Wallet-cli connect to java-stabila via gRPC protocol, which can be deployed locally or remotely. Check **Run a web Wallet** section.
We can configure java-stabila node IP and port in ``src/main/resources/config.conf``, so that wallet-cli server can successfully talk to java-stabila nodes.

## Wallet-cli supported command list

Following is a list of Stabila Wallet-cli commands:
For more information on a specific command, just type the command on terminal when you start your Wallet-cli.

| [AddTransactionSign](#How-to-use-the-multi-signature-feature-of-wallet-cli) | [ApproveProposal](#How-to-operate-with-proposal) | [AssetIssue](#How-to-issue-a-SRC10-token) |
| :---------:|:---------:|:--------: |
| [BackupShieldedSRC20Wallet](#BackupShieldedSRC20Wallet) | [BackupWallet](#Wallet-related-commands)| [BackupWallet2Base64](#Wallet-related-commands) |
| [BroadcastTransaction](#Some-others) | [ChangePassword](#Wallet-related-commands)| [CreateProposal](#How-to-operate-with-proposal) 
| [DeleteProposal](#Delete-an-existing-proposal) | [DeployContract](#How-to-use-smart-contract) | [ExchangeCreate](#How-to-trade-on-the-exchange) |
| [ExchangeInject](#Capital-injection) | [ExchangeTransaction](#Transactions) | [ExchangeWithdraw](#Capital-Withdrawal) |
| [CdBalance](#How-to-cd/uncd-balance) | [GenerateAddress](#Account-related-commands) | [GenerateShieldedSRC20Address](#How-to-transfer-shielded-SRC20-token)|
| [GetAccount](#Account-related-commands) |[GetAccountNet](#Account-related-commands) | [GetAccountResource](#Account-related-commands) | 
| [GetAddress](#Account-related-commands) | [GetAkFromAsk](#How-to-transfer-shielded-SRC20-token) |[GetAssetIssueByAccount](#Update-parameters-of-SRC10-token) | 
| [GetAssetIssueById](#How-to-obtain-SRC10-token-information) | [GetAssetIssueByName](#How-to-obtain-SRC10-token-information) |[GetAssetIssueListByName](#How-to-obtain-SRC10-token-information) | 
| [GetBalance](#Account-related-commands) | [GetBlock](#How-to-get-block-information) |[GetBlockById](#How-to-get-block-information) | 
| [GetBlockByLatestNum](#How-to-get-block-information) | [GetBlockByLimitNext](#How-to-get-block-information) | [GetBrokerage](#Brokerage) | 
| [GetContract](#get-details-of-a-smart-contract) | [GetDelegatedResource](#How-to-delegate-resource) |[GetDelegatedResourceAccountIndex](#get-resource-delegation-information) | 
| [GetDiversifier](#GetDiversifier)| [GetExpandedSpendingKey](#How-to-transfer-shielded-SRC20-token)| [GetIncomingViewingKey](#GetIncomingViewingKey)  | 
| [GetMarketOrderByAccount](#GetMarketOrderByAccount)| [GetMarketOrderById](#GetMarketOrderById)| [GetMarketOrderListByPair](#GetMarketOrderListByPair)  | 
| [GetMarketPairList](#GetMarketPairList)| [GetMarketPriceByPair](#GetMarketPriceByPair)| [GetNextMaintenanceTime](#Some-others) | 
| [GetNkFromNsk](#How-to-transfer-shielded-SRC20-token) | [GetProposal](#Obtain-proposal-information) | [GetShieldedPaymentAddress](#How-to-transfer-shielded-SRC20-token)| 
| [GetSpendingKey](#How-to-transfer-shielded-SRC20-token) | [GetReward](#Brokerage) |  [GetTransactionApprovedList](#Get-signature-information-according-to-transactions) |
| [GetTransactionById](#How-to-get-transaction-information) | [GetTransactionCountByBlockNum](#How-to-get-transaction-information) | [GetTransactionInfoByBlockNum](#How-to-get-transaction-information) | 
| [GetTransactionInfoById](#How-to-get-transaction-information) | [GetTransactionSignWeight](#How-to-use-the-multi-signature-feature-of-wallet-cli) | [ImportShieldedSRC20Wallet](#ImportShieldedSRC20Wallet) | 
| [ImportWallet](#Wallet-related-commands) | [ImportWalletByBase64](#Wallet-related-commands) | [ListAssetIssue](#How-to-obtain-SRC10-token-information) | 
| [ListExchanges](#Obtain-information-on-trading-pairs) | [ListExchangesPaginated](#Obtain-information-on-trading-pairs) | [ListNodes](#Some-others) | 
| [ListShieldedSRC20Address](#ListShieldedSRC20Address) | [ListShieldedSRC20Note](#ListShieldedSRC20Note) | [ListProposals](#Obtain-proposal-information) | 
| [ListProposalsPaginated](#Obtain-proposal-information) | [ListExecutives](#Some-others) | [LoadShieldedSRC20Wallet](#LoadShieldedSRC20Wallet) | 
| [Login](#Command-line-operation-flow-example) | [MarketCancelOrder](#MarketCancelOrder) | [MarketSellAsset](#MarketSellAsset)| 
| [ParticipateAssetIssue](#Participating-in-the-issue-of-SRC10-token) | [RegisterWallet](#Wallet-related-commands) | [ResetShieldedSRC20Note](#ResetShieldedSRC20Note) | 
| [ScanShieldedSRC20NoteByIvk](#ScanShieldedSRC20NoteByIvk) |  [ScanShieldedSRC20NoteByOvk](#ScanShieldedSRC20NoteByOvk) |[SendCoin](#How-to-use-the-multi-signature-feature-of-wallet-cli) | 
| [SendShieldedSRC20Coin](#SendShieldedSRC20Coin) | [SendShieldedSRC20CoinWithoutAsk](#SendShieldedSRC20CoinWithoutAsk) | [SetShieldedSRC20ContractAddress](#SetShieldedSRC20ContractAddress) | 
| [ShowShieldedSRC20AddressInfo](#ShowShieldedSRC20AddressInfo) | [TransferAsset](#SRC10-token-transfer) | [TriggerContract](#trigger-smart-contarct) |
| [UncdAsset](#Uncd-SRC10-token) | [UncdBalance](#How-to-delegate-resource) |[UpdateAsset](#Update-parameters-of-SRC10-token) | 
| [UpdateBrokerage](#Brokerage) | [UpdateUcrLimit](#update-smart-contract-parameters) |[UpdateSetting](#update-smart-contract-parameters) | 
| [UpdateAccountPermission](#How-to-use-the-multi-signature-feature-of-wallet-cli) | [VoteExecutive](#How-to-vote) |

Type any one of the listed commands, to display how-to tips.

## How to cd/uncd balance

After the funds are cded, the corresponding number of shares and bandwidth will be obtained.
Shares can be used for voting and bandwidth can be used for trading.
The rules for the use and calculation of share and bandwidth are described later in this article.

**Cd operation is as follows:**

```console
> cdBalance [OwnerAddress] cded_balance cded_duration [ResourceCode:0 BANDWIDTH, 1 UCR] [receiverAddress]
```

OwnerAddress
> The address of the account that initiated the transaction, optional, default is the address of the login account.

cded_balance
> The amount of cded funds, the unit is Unit.
> The minimum value is **1000000 Unit(1STB)**.

cded_duration
> Cd time, this value is currently only allowed for **3 days**.

For example:

```console
> cdBalance 100000000 3 1 address
```

After the cd operation, cded funds will be transferred from Account Balance to Cded,
You can view cded funds from your account information.
After being uncded, it is transferred back to Balance by Cded, and the cded funds cannot be used for trading.

When more share or bandwidth is needed temporarily, additional funds may be cded to obtain additional share and bandwidth.
The uncded time is postponed until 3 days after the last cd operation

After the CDing time expires, funds can be UNCDed.

**Uncd operation is as follows:**

```console
> uncdBalance [OwnerAddress] ResourceCode(0 BANDWIDTH, 1 CPU) [receiverAddress]
```

## How to vote

Voting requires share. Share can be obtained by CDing funds.

- The share calculation method is: **1** unit of share can be obtained for every **1STB** cded.
- After UNCDing, previous vote will expire. You can avoid the invalidation of the vote by re-CDing and voting.

**NOTE** The Stabila Network only records the status of your last vote, which means that each of your votes will overwrite all previous voting results.

For example:

```console
> cdBalance 100000000 3 1 address  # Cd 10STB and acquire 10 units of shares

> voteexecutive 123455 executive1 4 executive2 6  # Cast 4 votes for executive1 and 6 votes for executive2 at the same time

> voteexecutive 123455 executive1 10  # Voted 10 votes for executive1
```

The final result of the above command was 10 votes for executive1 and 0 vote for executive2.

## Brokerage

After voting for the executive, you will receive the rewards. The executive has the right to decide the ratio of brokerage. The default ratio is 20%, and the executive can adjust it.

By default, if a executive is rewarded, he will receive 20% of the whole rewards, and 80% of the rewards will be distributed to his voters.

### GetBrokerage

View the ratio of brokerage of the executive.

    > getbrokerage OwnerAddress

OwnerAddress
> The address of the executive's account, it is a base58check type address.

### GetReward

Query unclaimed reward.

    > getreward OwnerAddress

OwnerAddress
> The address of the voter's account, it is a base58check type address.

### UpdateBrokerage

Update the ratio of brokerage, this command is usually used by a executive account.

    > updateBrokerage OwnerAddress brokerage

OwnerAddress
> The address of the executive's account, it is a base58check type address.

brokerage
> The ratio of brokerage you want to update to, the limit of it: 0-100.

For example:

```console
> getbrokerage SZ7U1WVBRLZ2umjizxqz3XfearEHhXKX7h  

> getreward  SNfu3u8jo1LDWerHGbzs2Pv88Biqd85wEY

> updateBrokerage SZ7U1WVBRLZ2umjizxqz3XfearEHhXKX7h 30
```

## How to calculate bandwidth

The bandwidth calculation rule is:

    constant * CdedFunds * days

Assuming cd 1STB（1_000_000 Unit), 3 days, bandwidth obtained = 1 * 1_000_000 * 3 = 3_000_000.

All contracts consume bandwidth, including transferring, transferring of assets, voting, CDing, etc.
Querying does not consume bandwidth. Each contract needs to consume **100_000 bandwidth**.

If a contract exceeds a certain time (**10s**), this operation does not consume bandwidth.

When the UNCDing operation occurs, the bandwidth is not cleared.
The next time the cd is performed, the newly added bandwidth is accumulated.

## How to withdraw balance

After each block is produced, the block award is sent to the account's allowance,
and a withdraw operation is allowed every **24 hours** from allowance to balance.
The funds in allowance cannot be locked or traded.

## How to create executive

Applying to become a executive account needs to consume **100_000STB**.
This part of the funds will be burned directly.

## How to create account

It is not allowed to create accounts directly. You can only create accounts by transferring funds to non-existing accounts.
Transferring to a non-existent account has minimum restriction amount of **1STB**.

## Command line operation flow example

```console
$ cd wallet-cli
$ ./gradlew build
$ ./gradlew run
> RegisterWallet 123456      (password = 123456)
> login 123456
> getAddress
address = SRfwwLDpr4excH4V4QzghLEsdYwkapTxnm'  # backup it!
> BackupWallet 123456
priKey = 075725cf903fc1f6d6267b8076fc2c6adece0cfd18626c33427d9b2504ea3cef'  # backup it!!! (BackupWallet2Base64 option)
> getbalance
Balance = 0
> AssetIssue TestSTB STB 75000000000000000 1 1 2 "2019-10-02 15:10:00" "2020-07-11" "just for test121212" www.test.com 100 100000 10000 10 10000 1
> getaccount SRfwwLDpr4excH4V4QzghLEsdYwkapTxnm
(Print balance: 9999900000
"assetV2": [
    {
        "key": "1000001",
        "value": 74999999999980000
    }
],)
  # (cost stb 1000 stb for assetIssue)
  # (You can query the stb balance and other asset balances for any account )
> TransferAsset SWzrEZYtwzkAxXJ8PatVrGuoSNsexejRiM 1000001 10000
```

## How to issue a SRC10 token

Each account can only issue **ONE** SRC10 token.

### Issue SRC10 tokens

    > AssetIssue [OwnerAddress] AssetName AbbrName TotalSupply StbNum AssetNum Precision StartDate EndDate Description Url FreeNetLimitPerAccount PublicFreeNetLimit CdedAmount0 CdedDays0 [...] CdedAmountN CdedDaysN

OwnerAddress (optional)
> The address of the account which initiated the transaction. 
> Default: the address of the login account.

AssetName
> The name of the issued SRC10 token

AbbrName
> The abbreviation of SRC10 token

TotalSupply
> TotalSupply = Account Balance of Issuer + All Cded Token Amount
> TotalSupply: Total Issuing Amount
> Account Balance Of Issuer: At the time of issuance
> All Cded Token Amount: Before asset transfer and the issuance

StbNum, AssetNum
>  These two parameters determine the exchange rate when the token is issued.
> Exchange Rate = StbNum / AssetNum
> AssetNum: Unit in base unit of the issued token
> StbNum: Unit in UNIT (0.000001 STB)

Precision
> Precision to how many decimal places  

FreeNetLimitPerAccount
> The maximum amount of bandwidth each account is allowed to use. Token issuers can cd STB to obtain bandwidth (TransferAssetContract only)

PublicFreeNetLimit
> The maximum total amount of bandwidth which is allowed to use for all accounts. Token issuers can cd STB to obtain bandwidth (TransferAssetContract only)

StartDate, EndDate
> The start and end date of token issuance. Within this period time, other users can participate in token issuance.

CdedAmount0 CdedDays0
> Amount and days of token cd. 
> CdedAmount0: Must be bigger than 0
> CdedDays0: Must between 1 and 3653.

Example:

```console
> AssetIssue TestSTB STB 75000000000000000 1 1 2 "2019-10-02 15:10:00" "2020-07-11" "just for test121212" www.test.com 100 100000 10000 10 10000 1
> GetAssetIssueByAccount SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ  # View published information
{
    "assetIssue": [
        {
            "owner_address": "SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ",
            "name": "TestSTB",
            "abbr": "STB",
            "total_supply": 75000000000000000,
            "cded_supply": [
                {
                    "cded_amount": 10000,
                    "cded_days": 1
                },
                {
                    "cded_amount": 10000,
                    "cded_days": 10
                }
            ],
            "stb_num": 1,
            "precision": 2,
            "num": 1,
            "start_time": 1570000200000,
            "end_time": 1594396800000,
            "description": "just for test121212",
            "url": "www.test.com",
            "free_asset_net_limit": 100,
            "public_free_asset_net_limit": 100000,
            "id": "1000001"
        }
    ]
}
```

### Update parameters of SRC10 token

    > UpdateAsset [OwnerAddress] newLimit newPublicLimit description url

Specific meaning of the parameters is the same as that of AssetIssue.

Example:

```console
> UpdateAsset 1000 1000000 "change description" www.changetest.com
> GetAssetIssueByAccount SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ  # View the modified information
{
    "assetIssue": [
        {
            "owner_address": "SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ",
            "name": "TestSTB",
            "abbr": "STB",
            "total_supply": 75000000000000000,
            "cded_supply": [
                {
                    "cded_amount": 10000,
                    "cded_days": 1
                },
                {
                    "cded_amount": 10000,
                    "cded_days": 10
                }
            ],
            "stb_num": 1,
            "precision": 2,
            "num": 1,
            "start_time": 1570000200000,
            "end_time": 1594396800000,
            "description": "change description",
            "url": "www.changetest.com",
            "free_asset_net_limit": 1000,
            "public_free_asset_net_limit": 1000000,
            "id": "1000001"
        }
    ]
}
```

### SRC10 token transfer

    > TransferAsset [OwnerAddress] ToAddress AssertID Amount

OwnerAddress (optional)
> The address of the account which initiated the transaction. 
> Default: the address of the login account.

ToAddress
> Address of the target account

AssertName
> SRC10 token ID
> Example: 1000001

Amount
> The number of SRC10 token to transfer

Example:

```console
> TransferAsset SN3zfjYUmMFK3ZsHSsrdJoNRtGkQmZLBLz 1000001 1000
> getaccount SN3zfjYUmMFK3ZsHSsrdJoNRtGkQmZLBLz  # View target account information after the transfer
address: SN3zfjYUmMFK3ZsHSsrdJoNRtGkQmZLBLz
    assetV2
    {
    id: 1000001
    balance: 1000
    latest_asset_operation_timeV2: null
    free_asset_net_usageV2: 0
    }
```

### Participating in the issue of SRC10 token

    > ParticipateAssetIssue [OwnerAddress] ToAddress AssetID Amount

OwnerAddress (optional)
> The address of the account which initiated the transaction. 
> Default: the address of the login account.

ToAddress
> Account address of SRC10 issuers

AssertName
> SRC10 token ID
> Example: 1000001

Amount
> The number of SRC10 token to transfers

The participation process must happen during the release of SRC10, otherwise an error may occur.

Example:

```console
> ParticipateAssetIssue SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ 1000001 1000
> getaccount SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW  # View remaining balance
address: SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW
assetV2
    {
    id: 1000001
    balance: 1000
    latest_asset_operation_timeV2: null
    free_asset_net_usageV2: 0
    }
```

### Uncd SRC10 token

To uncd all SRC10 token which are supposed to be uncded after the CDing period.

    > uncdasset [OwnerAddress]

## How to obtain SRC10 token information

ListAssetIssue
> Obtain all of the published SRC10 token information

GetAssetIssueByAccount
> Obtain SRC10 token information based on issuing address

GetAssetIssueById
> Obtain SRC10 token Information based on ID

GetAssetIssueByName
> Obtain SRC10 token Information based on names

GetAssetIssueListByName
> Obtain a list of SRC10 token information based on names

## How to operate with proposal

Any proposal-related operations, except for viewing operations, must be performed by committee members.

### Initiate a proposal

    > createProposal [OwnerAddress] id0 value0 ... idN valueN

OwnerAddress (optional)
> The address of the account which initiated the transaction. 
> Default: the address of the login account.

id0
> The serial number of the parameter. Every parameter of STABILA network has a serial number. Please refer to "http://stabilascan.org/#/sr/committee" 

Value0
> The modified value

In the example, modification No.4 (modifying token issuance fee) costs 1000STB as follows:

```console
> createProposal 4 1000
> listproposals  # View initiated proposal
{
    "proposals": [
        {
            "proposal_id": 1,
            "proposer_address": "SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ",
            "parameters": [
                {
                    "key": 4,
                    "value": 1000
                }
            ],
            "expiration_time": 1567498800000,
            "create_time": 1567498308000
        }
    ]
}
```

The corresponding id is 1.

### Approve / Disapprove a proposal

    > approveProposal [OwnerAddress] id is_or_not_add_approval

OwnerAddress (optional)
> The address of the account which initiated the transaction. 
> Default: the address of the login account.

id
> ID of the initiated proposal
> Example: 1

is_or_not_add_approval
> true for approve; false for disapprove

Example:

```console
> ApproveProposal 1 true  # in favor of the offer
> ApproveProposal 1 false  # Cancel the approved proposal
```

### Delete an existing proposal

    > deleteProposal [OwnerAddress] proposalId

proposalId
> ID of the initiated proposal
> Example: 1

The proposal must be canceled by the supernode that initiated the proposal.

Example：

    > DeleteProposal 1

### Obtain proposal information

ListProposals
> Obtain a list of initiated proposals

ListProposalsPaginated
> Use the paging mode to obtain the initiated proposal

GetProposal
> Obtain proposal information based on the proposal ID

## How to trade on the exchange

The trading and price fluctuations of trading pairs are in accordance with the [Bancor Agreement](https://storage.googleapis.com/website-bancor/2018/04/01ba8253-bancor_protocol_whitepaper_en.pdf).

### Create a trading pair

    > exchangeCreate [OwnerAddress] first_token_id first_token_balance second_token_id second_token_balance

OwnerAddress (optional)
> The address of the account which initiated the transaction.
> Default: the address of the login account.

First_token_id, first_token_balance
> ID and amount of the first token

second_token_id, second_token_balance
> ID and amount of the second token
>
> The ID is the ID of the issued SRC10 token. 
> If it is STB, the ID is "_". 
> The amount must be greater than 0, and less than 1,000,000,000,000,000.

Example:

    > exchangeCreate 1000001 10000 _ 10000
    # Create trading pairs with the IDs of 1000001 and STB, with amount 10000 for both.

### Capital injection

    > exchangeInject [OwnerAddress] exchange_id token_id quant

OwnerAddress (optional)
> The address of the account which initiated the transaction.
> Default: the address of the login account.

exchange_id
> The ID of the trading pair to be funded

token_id, quant
> TokenId and quantity (unit in base unit) of capital injection

When conducting a capital injection, depending on its quantity (quant), a proportion
of each token in the trading pair will be withdrawn from the account, and injected into the trading
pair. Depending on the difference in the balance of the transaction, the same amount of money for
the same token would vary.

### Transactions

    > exchangeTransaction [OwnerAddress] exchange_id token_id quant expected

OwnerAddress (optional)
> The address of the account which initiated the transaction.
> Default: the address of the login account.

exchange_id
> ID of the trading pair

token_id, quant
> The ID and quantity of tokens being exchanged, equivalent to selling

expected
> Expected quantity of another token

expected must be less than quant, or an error will be reported.

Example：

    > ExchangeTransaction 1 1000001 100 80

It is expected to acquire the 80 STB by exchanging 1000001 from the trading pair ID of 1, and the amount is 100.(Equivalent to selling an amount of 100 tokenID - 1000001, at a price of 80 STB, in trading pair ID - 1).

### Capital Withdrawal

    > exchangeWithdraw [OwnerAddress] exchange_id token_id quant

OwnerAddress (optional)
> The address of the account which initiated the transaction.
> Default: the address of the login account.

Exchange_id

> 
The ID of the trading pair to be withdrawn

Token_id, quant
> TokenId and quantity (unit in base unit) of capital withdrawal

When conducting a capital withdrawal, depending on its quantity (quant), a proportion of each token
in the transaction pair is withdrawn from the trading pair, and injected into the account. Depending on the difference in the balance of the transaction, the same amount of money for the same token would vary.

### Obtain information on trading pairs

ListExchanges
> List trading pairs

ListExchangesPaginated
> List trading pairs by page

## How to use the multi-signature feature of wallet-cli?

Multi-signature allows other users to access the account in order to better manage it. There are
three types of accesses:

- owner: access to the owner of account
- active: access to other features of accounts, and access that authorizes a certain feature. Block production authorization is not included if it's for executive purposes.
- executive: only for executive, block production authorization will be granted to one of the other users.

The rest of the users will be granted

```console
> Updateaccountpermission SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ \
{
  "owner_permission": {
    "type": 0,
    "permission_name": "owner",
    "threshold": 1,
    "keys": [
      {
        "address": "SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ",
        "weight": 1
      }
    ]
  },
  "executive_permission": {
    "type": 1,
    "permission_name": "owner",
    "threshold": 1,
    "keys": [
      {
        "address": "SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ",
        "weight": 1
      }
    ]
  },
  "active_permissions": [
    {
      "type": 2,
      "permission_name": "active12323",
      "threshold": 2,
      "operations": "7fff1fc0033e0000000000000000000000000000000000000000000000000000",
      "keys": [
        {
          "address": "SNhXo1GbRNCuorvYu5JFWN3m2NYr9QQpVR",
          "weight": 1
        },
        {
          "address": "SKwhcDup8L2PH5r6hxp5CQvQzZqJLmKvZP",
          "weight": 1
        }
      ]
    }
  ]
}
```

The account SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ gives the owner access to itself, active access to
SNhXo1GbRNCuorvYu5JFWN3m2NYr9QQpVR and TKwhcDup8L2PH5r6hxp5CQvQzZqJLmKvZP. Active access will
need signatures from both accounts in order to take effect.

If the account is not a executive, it's not necessary to set executive_permission, otherwise an error will occur.

### Signed transaction

    > SendCoin SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW 10000000000000000

Will show "Please confirm and input your permission id, if input y or Y means default 0, other
non-numeric characters will cancel transaction."

This will require the transfer authorization of active access. Enter: 2

Then select accounts and put in local password, i.e. SNhXo1GbRNCuorvYu5JFWN3m2NYr9QQpVR needs a
private key SNhXo1GbRNCuorvYu5JFWN3m2NYr9QQpVR to sign a transaction.

Select another account and enter the local password. i.e. SKwhcDup8L2PH5r6hxp5CQvQzZqJLmKvZP will
need a private key of SKwhcDup8L2PH5r6hxp5CQvQzZqJLmKvZP to sign a transaction.

The weight of each account is 1, threshold of access is 2. When the requirements are met, users
will be notified with “Send 10000000000000000 Unit to SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW
successful !!”.

This is how multiple accounts user multi-signature when using the same cli.
Use the instruction addTransactionSign according to the obtained transaction hex string if
signing at multiple cli. After signing, the users will need to broadcast final transactions
manually.

## Obtain weight information according to transaction

    > getTransactionSignWeight
    0a8c010a020318220860e195d3609c86614096eadec79d2d5a6e080112680a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412370a1541a7d8a35b260395c14aa456297662092ba3b76fc01215415a523b449890854c8fc460ab602df9f31fe4293f18808084fea6dee11128027094bcb8bd9d2d1241c18ca91f1533ecdd83041eb0005683c4a39a2310ec60456b1f0075b4517443cf4f601a69788f001d4bc03872e892a5e25c618e38e7b81b8b1e69d07823625c2b0112413d61eb0f8868990cfa138b19878e607af957c37b51961d8be16168d7796675384e24043d121d01569895fcc7deb37648c59f538a8909115e64da167ff659c26101

The information displays as follows:

```json
{
    "result":{
        "code":"PERMISSION_ERROR",
        "message":"Signature count is 2 more than key counts of permission : 1"
    },
    "permission":{
        "operations":"7fff1fc0033e0100000000000000000000000000000000000000000000000000",
        "keys":[
            {
                "address":"SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ",
                "weight":1
            }
        ],
        "threshold":1,
        "id":2,
        "type":"Active",
        "permission_name":"active"
    },
    "transaction":{
        "result":{
            "result":true
        },
        "txid":"7da63b6a1f008d03ef86fa871b24a56a501a8bbf15effd7aca635de6c738df4b",
        "transaction":{
            "signature":[
                "c18ca91f1533ecdd83041eb0005683c4a39a2310ec60456b1f0075b4517443cf4f601a69788f001d4bc03872e892a5e25c618e38e7b81b8b1e69d07823625c2b01",
                "3d61eb0f8868990cfa138b19878e607af957c37b51961d8be16168d7796675384e24043d121d01569895fcc7deb37648c59f538a8909115e64da167ff659c26101"
            ],
            "txID":"7da63b6a1f008d03ef86fa871b24a56a501a8bbf15effd7aca635de6c738df4b",
            "raw_data":{
                "contract":[
                    {
                        "parameter":{
                            "value":{
                                "amount":10000000000000000,
                                "owner_address":"SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ",
                                "to_address":"SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW"
                            },
                            "type_url":"type.googleapis.com/protocol.TransferContract"
                        },
                        "type":"TransferContract",
                        "Permission_id":2
                    }
                ],
                "ref_block_bytes":"0318",
                "ref_block_hash":"60e195d3609c8661",
                "expiration":1554123306262,
                "timestamp":1554101706260
            },
            "raw_data_hex":"0a020318220860e195d3609c86614096eadec79d2d5a6e080112680a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412370a1541a7d8a35b260395c14aa456297662092ba3b76fc01215415a523b449890854c8fc460ab602df9f31fe4293f18808084fea6dee11128027094bcb8bd9d2d"
        }
    }
}
```

### Get signature information according to transactions

    > getTransactionApprovedList
    0a8c010a020318220860e195d3609c86614096eadec79d2d5a6e080112680a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412370a1541a7d8a35b260395c14aa456297662092ba3b76fc01215415a523b449890854c8fc460ab602df9f31fe4293f18808084fea6dee11128027094bcb8bd9d2d1241c18ca91f1533ecdd83041eb0005683c4a39a2310ec60456b1f0075b4517443cf4f601a69788f001d4bc03872e892a5e25c618e38e7b81b8b1e69d07823625c2b0112413d61eb0f8868990cfa138b19878e607af957c37b51961d8be16168d7796675384e24043d121d01569895fcc7deb37648c59f538a8909115e64da167ff659c26101

```json
{
    "result":{

    },
    "approved_list":[
        "SKwhcDup8L2PH5r6hxp5CQvQzZqJLmKvZP",
        "SNhXo1GbRNCuorvYu5JFWN3m2NYr9QQpVR"
    ],
    "transaction":{
        "result":{
            "result":true
        },
        "txid":"7da63b6a1f008d03ef86fa871b24a56a501a8bbf15effd7aca635de6c738df4b",
        "transaction":{
            "signature":[
                "c18ca91f1533ecdd83041eb0005683c4a39a2310ec60456b1f0075b4517443cf4f601a69788f001d4bc03872e892a5e25c618e38e7b81b8b1e69d07823625c2b01",
                "3d61eb0f8868990cfa138b19878e607af957c37b51961d8be16168d7796675384e24043d121d01569895fcc7deb37648c59f538a8909115e64da167ff659c26101"
            ],
            "txID":"7da63b6a1f008d03ef86fa871b24a56a501a8bbf15effd7aca635de6c738df4b",
            "raw_data":{
                "contract":[
                    {
                        "parameter":{
                            "value":{
                                "amount":10000000000000000,
                                "owner_address":"SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ",
                                "to_address":"SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW"
                            },
                            "type_url":"type.googleapis.com/protocol.TransferContract"
                        },
                        "type":"TransferContract",
                        "Permission_id":2
                    }
                ],
                "ref_block_bytes":"0318",
                "ref_block_hash":"60e195d3609c8661",
                "expiration":1554123306262,
                "timestamp":1554101706260
            },
            "raw_data_hex":"0a020318220860e195d3609c86614096eadec79d2d5a6e080112680a2d747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e5472616e73666572436f6e747261637412370a1541a7d8a35b260395c14aa456297662092ba3b76fc01215415a523b449890854c8fc460ab602df9f31fe4293f18808084fea6dee11128027094bcb8bd9d2d"
        }
    }
}
```

## How to use smart contract

### deploy smart contracts

    > DeployContract [ownerAddress] contractName ABI byteCode constructor params isHex fee_limit consume_user_resource_percent origin_ucr_limit value token_value token_id(e.g: STBTOKEN, use # if don't provided) <library:address,library:address,...> <lib_compiler_version(e.g:v5)> library:address,...>

OwnerAddress
> The address of the account that initiated the transaction, optional, default is the address of the login account.

contractName
> Name of smart contract

ABI
> Compile generated ABI code

byteCode
> Compile generated byte code

constructor, params, isHex
> Define the format of the bytecode, which determines the way to parse byteCode from parameters

fee_limit
> Transaction allows for the most consumed STB

consume_user_resource_percent
> Percentage of user resource consumed, in the range [0, 100]

origin_ucr_limit
> The most amount of developer Ucr consumed by trigger contract once

value
> The amount of stb transferred to the contract account

token_value
> Number of STB10

token_id
> STB10 Id

Example:

```
> deployContract normalcontract544 [{"constant":false,"inputs":[{"name":"i","type":"uint256"}],"name": "findArgsByIndexTest","outputs":[{"name":"z","type":"uint256"}],"payable":false,"stateMutability":"nonpayable","type":"function"}]
608060405234801561001057600080fd5b50610134806100206000396000f3006080604052600436106100405763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663329000b58114610045575b600080fd5b34801561005157600080fd5b5061005d60043561006f565b60408051918252519081900360200190f35b604080516003808252608082019092526000916060919060208201838038833901905050905060018160008151811015156100a657fe5b602090810290910101528051600290829060019081106100c257fe5b602090810290910101528051600390829060029081106100de57fe5b6020908102909101015280518190849081106100f657fe5b906020019060200201519150509190505600a165627a7a72305820b24fc247fdaf3644b3c4c94fcee380aa610ed83415061ff9e65d7fa94a5a50a00029 # # false 1000000000 75 50000 0 0 #
```

Get the result of the contract execution with the getTransactionInfoById command:

```console
> getTransactionInfoById 4978dc64ff746ca208e51780cce93237ee444f598b24d5e9ce0da885fb3a3eb9
{
    "id": "8c1f57a5e53b15bb0a0a0a0d4740eda9c31fbdb6a63bc429ec2113a92e8ff361",
    "fee": 6170500,
    "blockNumber": 1867,
    "blockTimeStamp": 1567499757000,
    "contractResult": [
        "6080604052600436106100405763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663329000b58114610045575b600080fd5b34801561005157600080fd5b5061005d60043561006f565b60408051918252519081900360200190f35b604080516003808252608082019092526000916060919060208201838038833901905050905060018160008151811015156100a657fe5b602090810290910101528051600290829060019081106100c257fe5b602090810290910101528051600390829060029081106100de57fe5b6020908102909101015280518190849081106100f657fe5b906020019060200201519150509190505600a165627a7a72305820b24fc247fdaf3644b3c4c94fcee380aa610ed83415061ff9e65d7fa94a5a50a00029"
    ],
    "contract_address": "SJMKWmC6mwF1QVax8Sy2AcgT6MqaXmHEds",
    "receipt": {
        "ucr_fee": 6170500,
        "ucr_usage_total": 61705,
        "net_usage": 704,
        "result": "SUCCESS"
    }
}
```

### trigger smart contarct

    > TriggerContract [ownerAddress] contractAddress method args isHex fee_limit value token_value token_id

OwnerAddress
> The address of the account that initiated the transaction, optional, default is the address of the login account.

contractAddress
> Smart contarct address

method
> The name of function and parameters, please refer to the example

args
> Parameter value, if you want to call `receive`, pass '#' instead

isHex
> The format of the parameters method and args, is hex string or not

fee_limit
> The most amount of stb allows for the consumption

token_value
> Number of STB10

token_id
> SRC10 id, If not, use ‘#’ instead

Example:

```console
> triggerContract SGdtALTPZ1FWQcc5MW7aK3o1ASaookkJxG findArgsByIndexTest(uint256) 0 false
1000000000 0 0 #
# Get the result of the contract execution with the getTransactionInfoById command
> getTransactionInfoById 7d9c4e765ea53cf6749d8a89ac07d577141b93f83adc4015f0b266d8f5c2dec4
{
    "id": "de289f255aa2cdda95fbd430caf8fde3f9c989c544c4917cf1285a088115d0e8",
    "fee": 8500,
    "blockNumber": 2076,
    "blockTimeStamp": 1567500396000,
    "contractResult": [
        ""
    ],
    "contract_address": "SJMKWmC6mwF1QVax8Sy2AcgT6MqaXmHEds",
    "receipt": {
        "ucr_fee": 8500,
        "ucr_usage_total": 85,
        "net_usage": 314,
        "result": "REVERT"
    },
    "result": "FAILED",
    "resMessage": "REVERT opcode executed"
}
```

### get details of a smart contract

    > GetContract contractAddress

contractAddress
> smart contract address

Example:

```console
> GetContract SGdtALTPZ1FWQcc5MW7aK3o1ASaookkJxG
{
    "origin_address": "SRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ",
    "contract_address": "SJMKWmC6mwF1QVax8Sy2AcgT6MqaXmHEds",
    "abi": {
        "entrys": [
            {
                "name": "findArgsByIndexTest",
                "inputs": [
                    {
                        "name": "i",
                        "type": "uint256"
                    }
                ],
                "outputs": [
                    {
                        "name": "z",
                        "type": "uint256"
                    }
                ],
                "type": "Function",
                "stateMutability": "Nonpayable"
            }
        ]
    },
    "bytecode": "608060405234801561001057600080fd5b50610134806100206000396000f3006080604052600436106100405763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663329000b58114610045575b600080fd5b34801561005157600080fd5b5061005d60043561006f565b60408051918252519081900360200190f35b604080516003808252608082019092526000916060919060208201838038833901905050905060018160008151811015156100a657fe5b602090810290910101528051600290829060019081106100c257fe5b602090810290910101528051600390829060029081106100de57fe5b6020908102909101015280518190849081106100f657fe5b906020019060200201519150509190505600a165627a7a72305820b24fc247fdaf3644b3c4c94fcee380aa610ed83415061ff9e65d7fa94a5a50a00029",
    "consume_user_resource_percent": 75,
    "name": "normalcontract544",
    "origin_ucr_limit": 50000,
    "code_hash": "23423cece3b4866263c15357b358e5ac261c218693b862bcdb90fa792d5714e6"
}
```

### update smart contract parameters

    > UpdateUcrLimit [ownerAddress] contract_address ucr_limit  # Update parameter ucr_limit
    > UpdateSetting [ownerAddress] contract_address consume_user_resource_percent  # Update parameter consume_user_resource_percent

## How to delegate resource

### delegate resource

    > cdBalance [OwnerAddress] cded_balance cded_duration [ResourceCode:0 BANDWIDTH, 1 UCR] [receiverAddress]

The latter two parameters are optional parameters. If not set, the STB is cded to obtain
resources for its own use; if it is not empty, the acquired resources are used by receiverAddress.

OwnerAddress
> The address of the account that initiated the transaction, optional, default is the address of the login account.

cded_balance
> The amount of cded STB, the unit is the smallest unit (Unit), the minimum is 1000000unit.

cded_duration
> frezen duration, 3 days

ResourceCode
> 0 BANDWIDTH;1 UCR

receiverAddress
> target account address

### uncd delegated resource

    > uncdBalance [OwnerAddress] ResourceCode(0 BANDWIDTH, 1 CPU) [receiverAddress]

The latter two parameters are optional. If they are not set, the BANDWIDTH resource is uncd
by default; when the receiverAddress is set, the delegate resources are uncdd.

### get resource delegation information

getDelegatedResource fromAddress toAddress
> get the information from the fromAddress to the toAddress resource delegate

getDelegatedResourceAccountIndex address
> get the information that address is delegated to other account resources

## Wallet related commands

**RegisterWallet**
> Register your wallet, you need to set the wallet password and generate the address and private key.

**BackupWallet**
> Back up your wallet, you need to enter your wallet password and export the private key.hex string format, such
as: 721d63b074f18d41c147e04c952ec93467777a30b6f16745bc47a8eae5076545

**BackupWallet2Base64**
> Back up your wallet, you need to enter your wallet password and export the private key.base64 format, such as: ch1jsHTxjUHBR+BMlS7JNGd3ejC28WdFvEeo6uUHZUU=

**ChangePassword**
> Modify the password of an account

**ImportWallet**
> Import wallet, you need to set a password, hex String format

**ImportWalletByBase64**
> Import wallet, you need to set a password, base64 fromat

## Account related commands

**GenerateAddress**
> Generate an address and print out the public and private keys

**GetAccount**
> Get account information based on address

**GetAccountNet**
> The usage of bandwidth

**GetAccountResource**
> The usage of bandwidth and ucr

**GetAddress**
> Get the address of the current login account

**GetBalance**
> Get the balance of the current login account

## How to get transaction information

**GetTransactionById**
> Get transaction information based on transaction id

**GetTransactionCountByBlockNum**
> Get the number of transactions in the block based on the block height

**GetTransactionInfoById**
> Get transaction-info based on transaction id, generally used to check the result of a smart contract trigger

**GetTransactionInfoByBlockNum**
> Get the list of transaction information in the block based on the block height

## How to get block information

**GetBlock**
> Get the block according to the block number; if you do not pass the parameter, get the latest block

**GetBlockById**
> Get block based on blockID

**GetBlockByLatestNum n**
> Get the latest n blocks, where 0 < n < 100

**GetBlockByLimitNext startBlockId endBlockId**
> Get the block in the range [startBlockId, endBlockId)

## Some others

**GetNextMaintenanceTime**
> Get the start time of the next maintain period

**ListNodes**
> Get other peer information

**ListExecutives**
> Get all miner node information

**BroadcastTransaction**
> Broadcast the transaction, where the transaction is in hex string format.

<!--  
## How to transfer to shielded address

### loadshieldedwallet

Load shielded address, shielded note and start to scan by ivk

Example:

```console
> loadshieldedwallet
Please input your password for shielded wallet.
> *******
LoadShieldedWallet successful !!!
```

### generateshieldedaddress number

Generate shielded addresses

number
> The number of shielded addresses, the default is 1

Example:

```console
> generateshieldedaddress 2
ShieldedAddress list:
zstabila165vh2d0qqj7ytrkjeehwy0sg3uvc4tnvcqnpqnzrqq4jpw2p7pzgm2d3chrwxk2jf9ck6rza8jr
zstabila1klw4nge0dz45axsyf5rq4tujmwernmwzzlq3s5wly3tewkf8d87zl66xt8seud0jkap2wpwkjcc
GenerateShieldedAddress successful !!
```

### listshieldedaddress

Display cached local shielded address list

Example:

```console
> listshieldedaddress
ShieldedAddress :
zstabila1akz7mt4zqsjqrdrwdsmffu6g5dnehhhtahjlc0c6syy3z9nxxjrzqszy22lyx326edmwqjhqe48
zstabila1ujhgjxazfnv8gzmkx0djn8cj4ef0mtfec6lkyslnslhf0mxlyg99ptk5hsuxmeqlqyakx7220ar
zstabila1vtf8ta7cztkk23pvs7euuh7jw6wzxhqr7pg48zznxt6cxel27ch3t9qhs8npeptdaqvf2sgwqfr
zstabila1lpfz287u6q3sfgdmfeh7n7dgmd7lq9780e858jzz0xeqssh0ahcfxg6wmhcqky744adjyk9nc0z
zstabila1m5dx50gryu789q5sh5207chzmmgzf5c7hvn8lr6xs60jfxvkv3d3h0kqkglc60rwq26dchztsty
zstabila165vh2d0qqj7ytrkjeehwy0sg3uvc4tnvcqnpqnzrqq4jpw2p7pzgm2d3chrwxk2jf9ck6rza8jr
```

### SendShieldedCoin

    > SendShieldedCoin [publicFromAddress] fromAmount shieldedInputNum input publicToAddress toAmount shieldedOutputNum shieldedAddress1 amount1 memo1 ...

Shielded transfer, support from public address or shielded address to public address and shielded address, does not support public address to public address, does not support automatic change.

Public input amount / shielded input amount = public output amount + shielded output amount + fee

publicFromAddress
> Public from address, set to null if not needed. Optional, If this variable is not configured, it is the address of the current login account

fromAmount
> The amount transfer from public address, if publicFromAddress set to null, this variable must be 0.

shieldedInputNum
> The number of shielded input note, should be 0 or 1.

input
> The index of shielded input note, get from execute command listshieldednote, if shieldedInputNum set to 0, no need to set.

publicToAddress
> Public to address, set to null if not needed.

toAmount
> The amount transfer to public address, if publicToAddress set to null, this variable must be 0.

shieldedOutputNum
> The amount of shielded output note. That is the number of (shieldedAddress amount meno) pairs, should be 0, 1, 2

shieldedAddress1
> Output shielded address

amount1
> The amount transfer to shieldedAddress1

memo1
> The memo of this note, up to 512 bytes, can be set to null if not needed

Example:

1. Public address transfer to shielded addresses
    **When in this mode,Some variables must be set as follows, shieldedInputNum=0, publicToAddress=null, toAmount=0**

    ```console
    > sendshieldedcoin TRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ 210000000 0 null 0 2 zstabila16j06s3p5gvp2jde4vh7w3ug3zz3m62zkyfu86s7ara5lafhp22p9wr3gz0lcdm3pvt7qx0aftu4 100000000 test1 zstabila1ghdy60hya8y72deu0q0r25qfl60unmue6889m3xfc3296a5ut6jcyafzhtp9nlutndukufzap4h 100000000 null
    ```

2. shielded address transfer to shielded address
    **When in this mode,Some variables must be set as follows, publicFromAddress=null, fromAmount=0, shieldedInputNum=1,publicToAddress=null,toAmount=0**

    ```console
    > listshieldednote
    Unspend note list like:
    1 zstabila1ghdy60hya8y72deu0q0r25qfl60unmue6889m3xfc3296a5ut6jcyafzhtp9nlutndukufzap4h 100000000 4ce5656a13049df00abc7fb3ce78d54c78944d3cbbdfdb29f288e1df5fdf67e1 1 UnSpend
    0 zstabila16j06s3p5gvp2jde4vh7w3ug3zz3m62zkyfu86s7ara5lafhp22p9wr3gz0lcdm3pvt7qx0aftu4 100000000 4ce5656a13049df00abc7fb3ce78d54c78944d3cbbdfdb29f288e1df5fdf67e1 0 UnSpend test1

    > sendshieldedcoin null 0 1 0 null 0 1 zstabila1hn9r3wmytavslztwmlzvuzk3dqpdhwcmda2d0deyu5pwv32dp78saaslyt82w0078y6uzfg8x6w 90000000 test2
    address zstabila16j06s3p5gvp2jde4vh7w3ug3zz3m62zkyfu86s7ara5lafhp22p9wr3gz0lcdm3pvt7qx0aftu4
    ```

3. shielded address transfer to public address
    **When in this mode,Some variables must be set as follows, publicFromAddress=null,fromAmount=0,shieldedInputNum=1,shieldedOutputNum=0**

    ```console
    > listshieldednote
    Unspend note list like:
    1 zstabila1ghdy60hya8y72deu0q0r25qfl60unmue6889m3xfc3296a5ut6jcyafzhtp9nlutndukufzap4h 100000000 4ce5656a13049df00abc7fb3ce78d54c78944d3cbbdfdb29f288e1df5fdf67e1 1 UnSpend
    2 zstabila1hn9r3wmytavslztwmlzvuzk3dqpdhwcmda2d0deyu5pwv32dp78saaslyt82w0078y6uzfg8x6w 90000000 06b55fc27f7ec649396706d149d18a0bb003347bdd7f489e3d47205da9cee802 0 UnSpend test2

    > sendshieldedcoin null 0 1 2 TRGhNNfnmgLegT4zHNjEqDSADjgmnHvubJ 80000000 0
    address zstabila1hn9r3wmytavslztwmlzvuzk3dqpdhwcmda2d0deyu5pwv32dp78saaslyt82w0078y6uzfg8x6w
    ```

### sendshieldedcoinwithoutask

Usage and parameters are consistent with the command sendshieldedcoin, the only difference is that sendshieldedcoin uses ask signature, but sendshieldedcoinwithoutask uses ak signature.

### listshieldednote type

List the note scanned by the local cache address

type
> Shows the type of note. If the variable is 0, it shows all unspent notes; For other values, it shows all the notes, including spent notes and unspent notes.

Example:

```console
> listshieldednote 0
Unspend note list like:
1 zstabila1ghdy60hya8y72deu0q0r25qfl60unmue6889m3xfc3296a5ut6jcyafzhtp9nlutndukufzap4h 100000000 4ce5656a13049df00abc7fb3ce78d54c78944d3cbbdfdb29f288e1df5fdf67e1 1 UnSpend
listshieldednote 1
All note list like:
zstabila1ghdy60hya8y72deu0q0r25qfl60unmue6889m3xfc3296a5ut6jcyafzhtp9nlutndukufzap4h 100000000 4ce5656a13049df00abc7fb3ce78d54c78944d3cbbdfdb29f288e1df5fdf67e1 1 UnSpend
zstabila16j06s3p5gvp2jde4vh7w3ug3zz3m62zkyfu86s7ara5lafhp22p9wr3gz0lcdm3pvt7qx0aftu4 100000000 4ce5656a13049df00abc7fb3ce78d54c78944d3cbbdfdb29f288e1df5fdf67e1 0 Spend test1
zstabila1hn9r3wmytavslztwmlzvuzk3dqpdhwcmda2d0deyu5pwv32dp78saaslyt82w0078y6uzfg8x6w 90000000 06b55fc27f7ec649396706d149d18a0bb003347bdd7f489e3d47205da9cee802 0 Spend test2
```

### resetshieldednote

Clean all the note scanned, rescanned all blocks.generally used when there is a problem with the notes or when switching environments

### ScanNotebyIvk ivk startNum endNum

Scan notes by ivk

ivk
> The ivk of shielded address

startNum
> The starting block number of the scan

endNum
> The end block number of the scan

Example:

    > scannotebyivk d2a4137cecf049965c4183f78fe9fc9fbeadab6ab3ef70ea749421b4c6b8de04 500 1499

### ScanNotebyOvk ovk startNum endNum

Scan notes by ovk

ovk
> the ivk of shielded address

startNum
> The starting block number of the scan

endNum
> The end block number of the scan

Example:

    > scannotebyovk a5b06ef3067855d741f966d54dfa1c124548535107333336bd9552a427f0529e 500 1499

### GetShieldedNullifier index

Get the nullifier of the note

index
> The note index obtained by the listshieldednote command

Example:

```console
> listshieldednote
Unspend note list like:
2 zstabila1ghdy60hya8y72deu0q0r25qfl60unmue6889m3xfc3296a5ut6jcyafzhtp9nlutndukufzap4h 100000000 4ce5656a13049df00abc7fb3ce78d54c78944d3cbbdfdb29f288e1df5fdf67e1 1 UnSpend
getshieldednullifier 2
address zstabila1ghdy60hya8y72deu0q0r25qfl60unmue6889m3xfc3296a5ut6jcyafzhtp9nlutndukufzap4h
value 100000000
rcm 07ed5471098652ad441575c61868d1e11317de0f73cbb743a4c5cfe78e3d150c
stbId 4ce5656a13049df00abc7fb3ce78d54c78944d3cbbdfdb29f288e1df5fdf67e1
index 1
memo
ShieldedNullifier:2a524a3be2643365ecdacf8f0d3ca1de8fad3080eea0b9561435b5d1ee467042
```

### ScanAndMarkNotebyAddress shieldedAddress startNum endNum

Scan the note with a locally cached shielded address and mark whether it is spent out

shieldedAddress
> Locally cached shielded address, if it is not a locally cached shielded address, an error will be reported.

startNum
> The starting block number of the scan

endNum
> The end block number of the scan

Example:

    > ScanAndMarkNotebyAddress zstabila16j06s3p5gvp2jde4vh7w3ug3zz3m62zkyfu86s7ara5lafhp22p9wr3gz0lcdm3pvt7qx0aftu4 500 1500

### GetSpendingKey

Generate a sk

Example:

```console
> GetSpendingKey
0eb458b309fa544066c40d80ce30a8002756c37d2716315c59a98c893dbb5f6a
```

### getExpandedSpendingKey sk

Generate ask, nsk, ovk from sk

Example:

```console
> getExpandedSpendingKey 0eb458b309fa544066c40d80ce30a8002756c37d2716315c59a98c893dbb5f6a
ask:252a0f6f6f0bac114a13e1e663d51943f1df9309649400218437586dea78260e
nsk:5cd2bc8d9468dbad26ea37c5335a0cd25f110eaf533248c59a3310dcbc03e503
ovk:892a10c1d3e8ea22242849e13f177d69e1180d1d5bba118c586765241ba2d3d6
```

### getAkFromAsk ask
Generate ak from ask

Example:

```console
> GetAkFromAsk 252a0f6f6f0bac114a13e1e663d51943f1df9309649400218437586dea78260e
ak:f1b843147150027daa5b522dd8d0757ec5c8c146defd8e01b62b34cf917299f1
```

### getNkFromNsk nsk

Generate nk from nsk

Example:

```console
> GetNkFromNsk 5cd2bc8d9468dbad26ea37c5335a0cd25f110eaf533248c59a3310dcbc03e503
nk:ed3dc885049f0a716a4de8c08c6cabcad0da3c437202341aa3d9248d8eb2b74a
```

### getIncomingViewingKey ak[64] nk[64]

Generate ivk from ak and nk

Example:

```console
> getincomingviewingkey f1b843147150027daa5b522dd8d0757ec5c8c146defd8e01b62b34cf917299f1 ed3dc885049f0a716a4de8c08c6cabcad0da3c437202341aa3d9248d8eb2b74a
ivk:148cf9e91f1e6656a41dc9b6c6ee4e52ff7a25b25c2d4a3a3182d0a2cd851205
```

### GetDiversifier

Generate a diversifier

Example:

```console
> GetDiversifier
11db4baf6bd5d5afd3a8b5
```

### getshieldedpaymentaddress ivk[64] d[22]

Generate a shielded address from sk and d

Example:

```console
GetShieldedPaymentAddress 148cf9e91f1e6656a41dc9b6c6ee4e52ff7a25b25c2d4a3a3182d0a2cd851205 11db4baf6bd5d5afd3a8b5
pkd:65c11642115d386ed716b9cc06a3498e86e303d7f20d0869c9de90e31322ac15
shieldedAddress:zstabila1z8d5htmt6h26l5agk4juz9jzz9wnsmkhz6uucp4rfx8gdccr6leq6zrfe80fpccny2kp2cray8z
```

### BackupShieldedWallet

Back up one shielded address

Example:

```console
wallet> BackUpShieldedWallet
Please input your password for shielded wallet.
password: 
The 1th shielded address is zstabila165gswmwecarmyph4x8jfrygezw78tejy3a8y5d9rxnlre7ju5q8jfsfe4qjerhfk0mmkzsx2t6t
The 2th shielded address is zstabila1hpd2aau0s55zaauu2dlnnu6umxcqz4wuhflu4p4uqpt9w0nqd88ucf036alw2zjfmclry4tnkf6
The 3th shielded address is zstabila19lgz39ja8dz427dt9qa8gpkpxanu05y09zplfzzwc640mlx74n4au3037nde3h6m7zsu5xgkrnn
Please choose between 1 and 3
2
sk:0c2dcfde42a484ecfcf6e7a00a3c9484022674739f405845d8d75fd6d8619153
d :b85aaef78f85282ef79c53
BackupShieldedWallet successful !!!
```

### ImportShieldedWallet

Import one shielded address to local wallet

Example:

```console
wallet> ImportShieldedWallet
Please input your password for shielded wallet.
password: 
Please input shielded wallet hex string. such as 'sk d',Max retry time:3
0b18ba69b7963d2ff47e69ac60c20dc30df34b221fa8960d7d61d68123999b8f  2fd028965d3b455579ab28
Import shielded wallet hex string is : 
sk:0b18ba69b7963d2ff47e69ac60c20dc30df34b221fa8960d7d61d68123999b8f
d :2fd028965d3b455579ab28
Import new shielded wallet address is: zstabila19lgz39ja8dz427dt9qa8gpkpxanu05y09zplfzzwc640mlx74n4au3037nde3h6m7zsu5xgkrnn
ImportShieldedWallet successful !!!
wallet> 
```

### ShowShieldedAddressInfo

Display information about shielded addresses

Example:

```console
> listshieldedaddress
ShieldedAddress :
zstabila14t95p936cyev678f6l6xsejnyfzrrzfsg56jaxgp7fzxlsczc2l6866fzc4c8awfnrzy74svkrl
zstabila1v6tu4c760vs7m0h94t89m4jcxtuq0nxmag7eequc3c2rnee3sufllq8fjtvfff6y84x3zgcapwp
zstabila18vaszshuluufz64uesvzw6wtune90uwexzmsfwtgqq2mlydt4fhy0kz02k3vm2j8er7s5xuyujv
> showshieldedaddressinfo zstabila18vaszshuluufz64uesvzw6wtune90uwexzmsfwtgqq2mlydt4fhy0kz02k3vm2j8er7s5xuyujv
The following variables are secret information, please don't show to other people!!!
sk :0deebe55fe7e591803126b531d4fe7c0e3979a2fcadb5a7996f73a8e463231f8
ivk:aa955c5798e3f611c72fa22842847810114dd5a860db272b2ef50cc8448ced00
ovk:a1d00b6f761137e1d8b58e77d8685347137131317ba3671f644ffb64bc5baa94
pkd:182769cbe4f257f1d930b704b9680015bf91abaa6e47d84f55a2cdaa47c8fd0a
d  :3b3b0142fcff38916abccc
> showshieldedaddressinfo zstabila19lgz39ja8dz427dt9qa8gpkpxanu05y09zplfzzwc640mlx74n4au3037nde3h6m7zsu5xgkrnn
pkd:3a7406c13767c7d08f2883f4884ec6aafdfcdeacebde45f1f4db98df5bf0a1ca
d  :2fd028965d3b455579ab28
```
-->

## How to transfer shielded SRC20 token

If you want to try to transfer shielded SRC20 token, you'd better set the `blockNumberStartToScan` field in `config.conf` file.
This field is used to set the starting block that the wallet needs to scan. If you ignore this field, or set it to 0, 
the notes you receive will probably take a long time to show up in the wallet. It is recommended that this field is 
set to the block number in which the earliest relevant shielded contract was created. If the exact number is not known, 
this field can be set as follows. If used in mainnet, please set 22690588. If used in Nile testnet, please set 6380000. 
Otherwise, please set 0.

When you begin to transfer SRC20 token to shielded address, you must have a shielded address. The
 following commands help to generate shielded account.

### GetSpendingKey

Generate a sk

Example:

```console
> GetSpendingKey
0eb458b309fa544066c40d80ce30a8002756c37d2716315c59a98c893dbb5f6a
```

### GetExpandedSpendingKey

```console
> GetExpandedSpendingKey sk
```
Generate ask, nsk, ovk from sk

Example:

```console
> GetExpandedSpendingKey 0eb458b309fa544066c40d80ce30a8002756c37d2716315c59a98c893dbb5f6a
ask:252a0f6f6f0bac114a13e1e663d51943f1df9309649400218437586dea78260e
nsk:5cd2bc8d9468dbad26ea37c5335a0cd25f110eaf533248c59a3310dcbc03e503
ovk:892a10c1d3e8ea22242849e13f177d69e1180d1d5bba118c586765241ba2d3d6
```

### GetAkFromAsk

```console
> GetAkFromAsk ask
```
Generate ak from ask

Example:

```console
> GetAkFromAsk 252a0f6f6f0bac114a13e1e663d51943f1df9309649400218437586dea78260e
ak:f1b843147150027daa5b522dd8d0757ec5c8c146defd8e01b62b34cf917299f1
```

### GetNkFromNsk

```console
> GetNkFromNsk nsk
```
Generate nk from nsk

Example:

```console
> GetNkFromNsk 5cd2bc8d9468dbad26ea37c5335a0cd25f110eaf533248c59a3310dcbc03e503
nk:ed3dc885049f0a716a4de8c08c6cabcad0da3c437202341aa3d9248d8eb2b74a
```

### GetIncomingViewingKey

```console
> GetIncomingViewingKey ak[64] nk[64]
```
Generate ivk from ak and nk

Example:

```console
> Getincomingviewingkey f1b843147150027daa5b522dd8d0757ec5c8c146defd8e01b62b34cf917299f1
 ed3dc885049f0a716a4de8c08c6cabcad0da3c437202341aa3d9248d8eb2b74a
ivk:148cf9e91f1e6656a41dc9b6c6ee4e52ff7a25b25c2d4a3a3182d0a2cd851205
```

### GetDiversifier

Generate a diversifier

Example:

```console
> GetDiversifier
11db4baf6bd5d5afd3a8b5
```

### GetShieldedPaymentAddress

```console
> GetShieldedPaymentAddress ivk[64] d[22]
```
Generate a shielded address from ivk and d

Example:

```console
> GetShieldedPaymentAddress 148cf9e91f1e6656a41dc9b6c6ee4e52ff7a25b25c2d4a3a3182d0a2cd851205
 11db4baf6bd5d5afd3a8b5
pkd:65c11642115d386ed716b9cc06a3498e86e303d7f20d0869c9de90e31322ac15
shieldedAddress:zstabila1z8d5htmt6h26l5agk4juz9jzz9wnsmkhz6uucp4rfx8gdccr6leq6zrfe80fpccny2kp2cray8z
```

### SetShieldedSRC20ContractAddress

```console
> SetShieldedSRC20ContractAddress SRC20ContractAddress ShieldedContractAddress
```
SRC20ContractAddress
> SRC20 contract address

ShieldedContractAddress
> Shielded contract address

Set SRC20 contract address and shielded contract address. Please execute this command before you perform all the following operations related to the shielded transaction of SRC20 token except `ScanShieldedSRC20NoteByIvk` and `ScanShieldedSRC20NoteByOvk`.

When you execute this command, the `Scaling Factor` will be shown. The `Scaling Factor` is set in
 the shielded contract. 



Example:

```console
> SetShieldedSRC20ContractAddress SLDxNTzNvEPd4gHox8V1zK2w82LFnideKE SKERuAmhJh8vZi1dzJtx8926xeCT74747e
scalingFactor():ed3437f8
SetShieldedSRC20ContractAddress succeed!
The Scaling Factor is 1000
That means:
No matter you MINT, TRANSFER or BURN, the value must be an integer multiple of 1000
```

### LoadShieldedSRC20Wallet

Load SRC20 shielded address, shielded note and start to scan by ivk.

Example:

```console
> LoadShieldedSRC20Wallet
Please input your password for shieldedSRC20 wallet.
> *******
LoadShieldedSRC20Wallet successful !!!
```

### GenerateShieldedSRC20Address

```console
> GenerateShieldedSRC20Address number
```
number
> The number of SRC20 shielded addresses, the default is 1.

Generate SRC20 shielded addresses.

Example:

```console
> GenerateShieldedSRC20Address 3
ShieldedSRC20Address list:
zstabila1da9rnkmnzl89kqq87gzh534xmkdhq9cnm0j39lackskrhflfe9d26chnq3adl86es0jm2098hzc
zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf
zstabila109r3w5gpm0qcf67r67a9ftjt3zy9wmzux4fqgtgcql8gwhcmauv5dm6t9t9x9ht7h3lvs8shxhq
GenerateShieldedSRC20Address successful !!!
```

### ListShieldedSRC20Address

Display cached local SRC20 shielded address list.

Example:

```console
> ListShieldedSRC20Address
ShieldedSRC20Address :
zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf
zstabila109r3w5gpm0qcf67r67a9ftjt3zy9wmzux4fqgtgcql8gwhcmauv5dm6t9t9x9ht7h3lvs8shxhq
zstabila1da9rnkmnzl89kqq87gzh534xmkdhq9cnm0j39lackskrhflfe9d26chnq3adl86es0jm2098hzc
zstabila1tjgkfk9hgrl0u6d07w3hq0s9jtgq9q64vek3e5l447dmnzhe27yy0ftpee45h07sa092wkrgrjl
zstabila15t3c27a5ve43ssflqepa8dke36vzvccxrren4ma2lghu3hle8rtwltufnvvzrm76w042s9p5f46
```

### SendShieldedSRC20Coin

> SendShieldedSRC20Coin fromAmount shieldedInputNum input1 input2 ... publicToAddress toAmount shieldedOutputNum shieldedAddress1 amount1 memo1 shieldedAddress2 amount2 memo2 ....

Shielded transfer, support three types:

- MINT: transfer from one public address to one shielded address, fromAmount should be equal to
 the shielded output amount. When you MINT, you need to enter password twice as prompted, one
 time is for triggering `approve` method of SRC20 contract that allows the shielded contract can
 transfer form your account, and other one is for triggering `mint` method of shielded contract
 that executes MINT. It's important to remember that you must use the same public address to
 trigger these two methods. 
- TRANSFER: transfer from one or two shielded address(es) to one or two shielded address(es), the
 sum of shielded input amount should be equal to the sum of shielded output amount. When you
 TRANSFER, you need to enter password of public account as prompted, and this is used to trigger
 'transfer' method of shielded contract that executes TRANSFER. 
- BURN: transfer from one shielded address to one public address and one optional shielded
 address. If there is no shielded output, toAmount should be equal to the shielded input amount
 , otherwise, the sum of toAmount and shielded output amount should be equal to the shielded
  input amount. When you BURN, you need to enter password of public account as prompted, and
 this is used to trigger 'burn' method of shielded contract that executes BURN. 

It's better to use different accounts to trigger BURN, TRANSFER and MINT.

fromAmount
> The amount transfer from public address. If the transfer type is MINT, this variable must be equal to the shielded output amount, otherwise it must be 0.

shieldedInputNum
> The number of shielded input note, should be 0, 1 or 2. If the transfer type is MINT, this variable must be 0; if BURN, it must be 1.

input1/input2
> The index of shielded input note, get from executing command ListShieldedSRC20Note. If shieldedInputNum set to 0, no need to set.

publicToAddress
> Public to address. If the transfer type is BURN, this variable must be a valid address, otherwise it should be set null.

toAmount
> The amount transfer to public address. If the transfer type is BURN, this variable must be equal to the shielded input amount, otherwise it should be 0.

shieldedOutputNum
> The amount of shielded output note. That is the number of (shieldedAddress amount memo) pairs, should be 0, 1 or 2.

shieldedAddress1/shieldedAddress2
> Output shielded address

amount1/amount2
> The amount transfer to shieldedAddress1/shieldedAddress2

memo1/memo2
> The memo of this note, up to 512 bytes, can be set to null if not needed.

Example:

In this example, the scalingFactor is 1000. 

1. MINT
   
    **In this mode, some variables must be set as follows, shieldedInputNum = 0, publicToAddress = null, toAmount = 0.**

    ```console
    > SendShieldedSRC20Coin 1000000000000 0 null 0 1 zstabila15t3c27a5ve43ssflqepa8dke36vzvccxrren4ma2lghu3hle8rtwltufnvvzrm76w042s9p5f46 1000000000000 null
    ```

2. TRANSFER
   
    **In this mode, some variables must be set as follows, fromAmount = 0, publicToAddress = null,toAmount = 0.**

    Transfer from one shielded address to one shielded address.
    ```console
    > ListShieldedSRC20Note
    This command will show all the unspent notes.
    If you want to display all notes, including spent notes and unspent notes, please use command ListShieldedSRC20Note 1
    The unspent note list is shown below:
    9 zstabila1tjgkfk9hgrl0u6d07w3hq0s9jtgq9q64vek3e5l447dmnzhe27yy0ftpee45h07sa092wkrgrjl 2000000000000 23f171f6552680b553707715bead8de807a70255c0b091f7e788bf3b59fe3bea 1 UnSpend
    8 zstabila15t3c27a5ve43ssflqepa8dke36vzvccxrren4ma2lghu3hle8rtwltufnvvzrm76w042s9p5f46 1000000000000 23f171f6552680b553707715bead8de807a70255c0b091f7e788bf3b59fe3bea 0 UnSpend
    The Scaling Factor is 1000
    No matter you MINT, TRANSFER or BURN, the value must be an integer multiple of 1000
    
    > SendShieldedSRC20Coin 0 1 8 null 0 1 zstabila1da9rnkmnzl89kqq87gzh534xmkdhq9cnm0j39lackskrhflfe9d26chnq3adl86es0jm2098hzc 1000000000000 null
    ```

    Transfer from one shielded address to two shielded addresses.
    ```console
    > ListShieldedSRC20Note
    This command will show all the unspent notes.
    If you want to display all notes, including spent notes and unspent notes, please use command ListShieldedSRC20Note 1
    The unspent note list is shown below:
    9 zstabila1tjgkfk9hgrl0u6d07w3hq0s9jtgq9q64vek3e5l447dmnzhe27yy0ftpee45h07sa092wkrgrjl 2000000000000 23f171f6552680b553707715bead8de807a70255c0b091f7e788bf3b59fe3bea 1 UnSpend
    10 zstabila1da9rnkmnzl89kqq87gzh534xmkdhq9cnm0j39lackskrhflfe9d26chnq3adl86es0jm2098hzc 1000000000000 81a06080f2be3f795c506826e066b9bb5327ca234eb31a0ef2446e11339a3935 0 UnSpend
    The Scaling Factor is 1000
    No matter you MINT, TRANSFER or BURN, the value must be an integer multiple of 1000
    
    > SendShieldedSRC20Coin 0 1 9 null 0 2 zstabila1da9rnkmnzl89kqq87gzh534xmkdhq9cnm0j39lackskrhflfe9d26chnq3adl86es0jm2098hzc 1500000000000 test1 zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 500000000000 null
    ```

    Transfer from two shielded addresses to one shielded address.
    ```console
    > ListShieldedSRC20Note
    This command will show all the unspent notes.
    If you want to display all notes, including spent notes and unspent notes, please use command ListShieldedSRC20Note 1
    The unspent note list is shown below:
    11 zstabila1da9rnkmnzl89kqq87gzh534xmkdhq9cnm0j39lackskrhflfe9d26chnq3adl86es0jm2098hzc 1500000000000 35901973a96369618e5e3f7f4dcede2b5ddb5bc99bf6feac29f2706420ea99c0 0 UnSpend test1
    10 zstabila1da9rnkmnzl89kqq87gzh534xmkdhq9cnm0j39lackskrhflfe9d26chnq3adl86es0jm2098hzc 1000000000000 81a06080f2be3f795c506826e066b9bb5327ca234eb31a0ef2446e11339a3935 0 UnSpend
    12 zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 500000000000 35901973a96369618e5e3f7f4dcede2b5ddb5bc99bf6feac29f2706420ea99c0 1 UnSpend
    The Scaling Factor is 1000
    No matter you MINT, TRANSFER or BURN, the value must be an integer multiple of 1000    
    
    > SendShieldedSRC20Coin 0 2 10 11 null 0 1 zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 2500000000000 null
    ```

    Transfer from two shielded addresses to two shielded addresses.
    ```console
    > ListShieldedSRC20Note
    This command will show all the unspent notes.
    If you want to display all notes, including spent notes and unspent notes, please use command ListShieldedSRC20Note 1
    The unspent note list is shown below:
    13 zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 2500000000000 6ec74435e32261a6dfe10f9498b3ab5a5cfede7c4e31299752b449b9506efc11 0 UnSpend
    12 zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 500000000000 35901973a96369618e5e3f7f4dcede2b5ddb5bc99bf6feac29f2706420ea99c0 1 UnSpend   
    The Scaling Factor is 1000
    No matter you MINT, TRANSFER or BURN, the value must be an integer multiple of 1000
    
    > SendShieldedSRC20Coin 0 2 12 13 null 0 2 zstabila15t3c27a5ve43ssflqepa8dke36vzvccxrren4ma2lghu3hle8rtwltufnvvzrm76w042s9p5f46 1300000000000 null zstabila1tjgkfk9hgrl0u6d07w3hq0s9jtgq9q64vek3e5l447dmnzhe27yy0ftpee45h07sa092wkrgrjl 1700000000000 null
    ```

3. BURN 

    **In this mode, some variables must be set as follows, fromAmount = 0, shieldedInputNum = 1.**
    ```console
    > ListShieldedSRC20Note
    This command will show all the unspent notes.
    If you want to display all notes, including spent notes and unspent notes, please use command ListShieldedSRC20Note 1
    The unspent note list is shown below:
    15 zstabila1tjgkfk9hgrl0u6d07w3hq0s9jtgq9q64vek3e5l447dmnzhe27yy0ftpee45h07sa092wkrgrjl 1700000000000 7291b2c58cafb4dede626388f12e846470441f9bb05581221fd742bdd8909a24 1 UnSpend
    14 zstabila15t3c27a5ve43ssflqepa8dke36vzvccxrren4ma2lghu3hle8rtwltufnvvzrm76w042s9p5f46 1300000000000 7291b2c58cafb4dede626388f12e846470441f9bb05581221fd742bdd8909a24 0 UnSpend
    The Scaling Factor is 1000
    No matter you MINT, TRANSFER or BURN, the value must be an integer multiple of 1000

    > SendShieldedSRC20Coin 0 1 14 SDVr15jvAx6maR28tP7RRpxuKZ38tgsyNE 1300000000000000 0
    > SendShieldedSRC20Coin 0 1 14 SDVr15jvAx6maR28tP7RRpxuKZ38tgsyNE 300000000000000 1 zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 1000000000000000 null
    ```

### SendShieldedSRC20CoinWithoutAsk

Usage and parameters are consistent with the command SendShieldedSRC20Coin, the only difference is that SendShieldedSRC20Coin uses ask for signature, but SendShieldedSRC20CoinWithoutAsk uses ak.

### ListShieldedSRC20Note

```console
> ListShieldedSRC20Note type
```
type
> Shows the type of note. If the variable is omitted or set to 0, it shows all unspent notes; For other values, it shows all the notes, including spent notes and unspent notes.

List the note scanned by the local cache address, and the `Scaling Factor`.

**NOTE** When you load shielded wallet, the wallet will scan blocks to find the notes others send to you in the backend. This will take a long time, so when you run `ListShieldedSRC20Note`, your notes will not be displayed immediately.

Example:

```console
> ListShieldedSRC20Note
This command will show all the unspent notes.
If you want to display all notes, including spent notes and unspent notes, please use command ListShieldedSRC20Note 1
The unspent note list is shown below:
15 zstabila1tjgkfk9hgrl0u6d07w3hq0s9jtgq9q64vek3e5l447dmnzhe27yy0ftpee45h07sa092wkrgrjl 1700000000000 7291b2c58cafb4dede626388f12e846470441f9bb05581221fd742bdd8909a24 1 UnSpend
The Scaling Factor is 1000
No matter you MINT, TRANSFER or BURN, the value must be an integer multiple of 1000

> ListShieldedSRC20Note 1
All notes are shown below:
zstabila1tjgkfk9hgrl0u6d07w3hq0s9jtgq9q64vek3e5l447dmnzhe27yy0ftpee45h07sa092wkrgrjl 1700000000000 7291b2c58cafb4dede626388f12e846470441f9bb05581221fd742bdd8909a24 1 15 UnSpent
zstabila15t3c27a5ve43ssflqepa8dke36vzvccxrren4ma2lghu3hle8rtwltufnvvzrm76w042s9p5f46 1000000000000 dc02678b0cf1c93c557dc805edb776fe79201c77f210f08f60cea5d687b14f2e 0 0 Spent
zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 1000000000000 e4d35d147762020078d7d197c98fffde181250e4a637d4bdd9ca809116d74131 0 2 Spent
zstabila15t3c27a5ve43ssflqepa8dke36vzvccxrren4ma2lghu3hle8rtwltufnvvzrm76w042s9p5f46 1000000000000 1594e1ee06c8420a4f1d80670000cd9268a2ff4e97e3f630909feeb51a9de993 0 3 Spent
zstabila15t3c27a5ve43ssflqepa8dke36vzvccxrren4ma2lghu3hle8rtwltufnvvzrm76w042s9p5f46 2000000000000 3f035e966b3ef636ae9c0a0f64bff781b1d1a8b52bab5d8124c0f9162f71f68f 0 1 Spent
zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 300000000000 6929757cb86cb6cf3e89df19f3212c3e62070b12d8b36de48e663fed214a4082 0 4 Spent test1
zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 2000000000000 e39a1e1d5af7dcbab0d55a63a0c62ec9cc7c0aaf8ce98733802674c3ec1f3a06 0 6 Spent
zstabila109r3w5gpm0qcf67r67a9ftjt3zy9wmzux4fqgtgcql8gwhcmauv5dm6t9t9x9ht7h3lvs8shxhq 700000000000 6929757cb86cb6cf3e89df19f3212c3e62070b12d8b36de48e663fed214a4082 1 5 Spent
zstabila109r3w5gpm0qcf67r67a9ftjt3zy9wmzux4fqgtgcql8gwhcmauv5dm6t9t9x9ht7h3lvs8shxhq 2300000000000 4ce1ce9f6377ee3cd936757b696ac43ecc39ee6e8a0eab1b8f8ef093e15010f8 0 7 Spent
zstabila15t3c27a5ve43ssflqepa8dke36vzvccxrren4ma2lghu3hle8rtwltufnvvzrm76w042s9p5f46 1000000000000 23f171f6552680b553707715bead8de807a70255c0b091f7e788bf3b59fe3bea 0 8 Spent
zstabila1tjgkfk9hgrl0u6d07w3hq0s9jtgq9q64vek3e5l447dmnzhe27yy0ftpee45h07sa092wkrgrjl 2000000000000 23f171f6552680b553707715bead8de807a70255c0b091f7e788bf3b59fe3bea 1 9 Spent
zstabila1da9rnkmnzl89kqq87gzh534xmkdhq9cnm0j39lackskrhflfe9d26chnq3adl86es0jm2098hzc 1000000000000 81a06080f2be3f795c506826e066b9bb5327ca234eb31a0ef2446e11339a3935 0 10 Spent
zstabila1da9rnkmnzl89kqq87gzh534xmkdhq9cnm0j39lackskrhflfe9d26chnq3adl86es0jm2098hzc 1500000000000 35901973a96369618e5e3f7f4dcede2b5ddb5bc99bf6feac29f2706420ea99c0 0 11 Spent test1
zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 500000000000 35901973a96369618e5e3f7f4dcede2b5ddb5bc99bf6feac29f2706420ea99c0 1 12 Spent
zstabila1mm20lkcpj6tx6jfd6ek5fxkgmpk9f2hda6vxdtkwlzr45ez32wa7dt8uka9xwfqamr7zyk7jpzf 2500000000000 6ec74435e32261a6dfe10f9498b3ab5a5cfede7c4e31299752b449b9506efc11 0 13 Spent
zstabila15t3c27a5ve43ssflqepa8dke36vzvccxrren4ma2lghu3hle8rtwltufnvvzrm76w042s9p5f46 1300000000000 7291b2c58cafb4dede626388f12e846470441f9bb05581221fd742bdd8909a24 0 14 Spent
The Scaling Factor is 1000
No matter you MINT, TRANSFER or BURN, the value must be an integer multiple of 1000
```

### ResetShieldedSRC20Note

Clean all the notes scanned, and rescan all blocks. Generally used when there is a problem with the notes or when switching environments.

### ScanShieldedSRC20NoteByIvk

```console
> ScanShieldedSRC20NoteByIvk shieldedSRC20ContractAddress ivk ak nk startNum endNum [event1] [event2] ...
```

shieldedSRC20ContractAddress
> The address of shielded contract

ivk
> The ivk of shielded address

ak
> The ak of shielded address. Please set this field to null, if you don't care whether the notes are spent or not.

nk
> The nk of shielded address. Please set this field to null, if you don't care whether the notes are spent or not.

startNum
> The starting block number of the scan

endNum
> The end block number of the scan

event1/event2
> The events you want to scan. These events must be compatible with standard events, that is, MintNewLeaf(uint256,bytes32,bytes32,bytes32,bytes32[21]), TransferNewLeaf(uint256,bytes32,bytes32,bytes32,bytes32[21]) and BurnNewLeaf(uint256,bytes32,bytes32,bytes32,bytes32[21]). If you ignore this field, the command will scan the standard events. In most cases, you can ignore these parameters.

Scan notes by ivk, ak and nk.

Example:

```console
> ScanShieldedSRC20NoteByIvk SVqa39sqP8ZJNTWjtKrDRifGdVmA4Ycsxu fed8fa4714e6a19511760f9b8ed33388f14c626adff26034f4a21557cb928f01 faf63a2d959df05d4441c0fd42262e0a53629c532e8d29501fe94f9d86c51313 66458c23d737a30146533374d7c5c78f3e05f8f158192e8855493cc55cf8953f 5000 5400
[
    {
        note: {
            value: 100000
            payment_address:
            zstabila12dq4ktrydrxzxrsgpmusp4pe0xawqyz4qfxzsgjdauw99n4n3efnw4kmrptlw8jcrrydx5694mw
            rcm: a45878a4e0d53f5cac79370fea1bf4aa82c67d3b2f647ac89c2b1e7061ea740a
            memo: without ask 2v1
        }
        position: 10
        is_spent: true
        tx_id: 5891fd3a8e860b336b7f7d31f64ec52ec5dc76f81b9bb4e4d0fa8a5756a61dd6
    }
]

> ScanShieldedSRC20NoteByIvk SVqa39sqP8ZJNTWjtKrDRifGdVmA4Ycsxu fed8fa4714e6a19511760f9b8ed33388f14c626adff26034f4a21557cb928f01  faf63a2d959df05d4441c0fd42262e0a53629c532e8d29501fe94f9d86c51313 66458c23d737a30146533374d7c5c78f3e05f8f158192e8855493cc55cf8953f 5000  6000 MintNewLeaf(uint256,bytes32,bytes32,bytes32,bytes32[21])
[
    {
        note: {
            value: 100000
            payment_address: zstabila1z8d5htmt6h26l5agk4ywv86xv3shuv4gjc2rzufyz4s2g5x0035nwrcqmxj4a49n2dy5sq28s5p
            rcm: 07604b4a8018d353c08f93044df0fc04ef988c2f65f9222eacc8d41f0e095404
            memo: mint
        }
        position: 16
        is_spent: false
        tx_id: 38d759216f62503c2b8bf7fc9777e6e25f5f77ec22dd760cc03057c4704277a2
    }
] 

> ScanShieldedSRC20NoteByIvk TVqa39sqP8ZJNTWjtKrDRifGdVmA4Ycsxu fed8fa4714e6a19511760f9b8ed33388f14c626adff26034f4a21557cb928f01 faf63a2d959df05d4441c0fd42262e0a53629c532e8d29501fe94f9d86c51313 66458c23d737a30146533374d7c5c78f3e05f8f158192e8855493cc55cf8953f 5000 5400 BurnNewLeaf(uint256,bytes32,bytes32,bytes32,bytes32[21])
[
    {
        note: {
            value: 100000
            payment_address: zstabila12dq4ktrydrxzxrsgpmusp4pe0xawqyz4qfxzsgjdauw99n4n3efnw4kmrptlw8jcrrydx5694mw
            rcm: a45878a4e0d53f5cac79370fea1bf4aa82c67d3b2f647ac89c2b1e7061ea740a
            memo: without ask 2v1
        }
        position: 10
        is_spent: true
        tx_id: 5891fd3a8e860b336b7f7d31f64ec52ec5dc76f81b9bb4e4d0fa8a5756a61dd6
    }
]
```

## ScanShieldedSRC20NoteByOvk

```console
> ScanShieldedSRC20NoteByOvk shieldedSRC20ContractAddress ovk startNum endNum [event1] [event2] ...
```
shieldedSRC20ContractAddress
> The address of shielded contract

ovk
> the ovk of shielded address

startNum
> The starting block number of the scan

endNum
> The end block number of the scan

event1/event2
> The event you want to scan. These events must be compatible with standard events, that is, MintNewLeaf(uint256,bytes32,bytes32,bytes32,bytes32[21]), TransferNewLeaf(uint256,bytes32,bytes32,bytes32,bytes32[21]), BurnNewLeaf(uint256,bytes32,bytes32,bytes32,bytes32[21]) and TokenBurn(address,uint256,bytes32[3]). 
If you ignore this field, the command will scan the standard events.

Scan notes by ovk

Example:

```console
> ScanShieldedSRC20NoteByOvk SVqa39sqP8ZJNTWjtKrDRifGdVmA4Ycsxu 4b33fc947a53a5e2a1d1636b323f7f6cecff8c34c9fc511ccc7cfaf0dd6f4c03 5000 6000
[
    {
        note: {
            value: 60000
            payment_address: zstabila1z8d5htmt6h26l5agk5nlxdlz66fahhcp8vwhyydrwfdajc5yalftew5uhwn6wjz4pwrxu0msu34
            rcm: 50698dc3c97fb4d2c818b62de2265a271eb9a58b5dd65074122ddf4d794c6b03
            memo: 1
        }
        tx_id: 19c8aaa244dbcdf30a4b2a02b9b17054dc5d8ebf41d1f82daea044e65dff29d5
    }
    {
        note: {
            value: 40000
            payment_address: zstabila1z8d5htmt6h26l5agk5nlxdlz66fahhcp8vwhyydrwfdajc5yalftew5uhwn6wjz4pwrxu0msu34
            rcm: 94afb02c6fd4b19ada89b6b85e2cc23f2fb76c5188ede646c5046b2539a3bf00
            memo: 2
        }
        tx_id: 19c8aaa244dbcdf30a4b2a02b9b17054dc5d8ebf41d1f82daea044e65dff29d5
    }
    {
        transparent_to_address: SV7ceN4tHDNPB47DMStcUFC3Y8QQ7KzN32
        transparent_amount: 130000
        tx_id: d45da3394be6c15220d31ac17c13e02130aab0c3edf97750620538f4efae366b
    }
]

> ScanShieldedSRC20NoteByOvk SVqa39sqP8ZJNTWjtKrDRifGdVmA4Ycsxu 4b33fc947a53a5e2a1d1636b323f7f6cecff8c34c9fc511ccc7cfaf0dd6f4c03 5000 6000  BurnNewLeaf(uint256,bytes32,bytes32,bytes32,bytes32[21])  TokenBurn(address,uint256,bytes32[3])
[
    {
        note: {
            value: 60000
            payment_address: zstabila1z8d5htmt6h26l5agk5nlxdlz66fahhcp8vwhyydrwfdajc5yalftew5uhwn6wjz4pwrxu0msu34
            rcm: 50698dc3c97fb4d2c818b62de2265a271eb9a58b5dd65074122ddf4d794c6b03
            memo: 1
        }
        tx_id: 19c8aaa244dbcdf30a4b2a02b9b17054dc5d8ebf41d1f82daea044e65dff29d5
    }
    {
        note: {
            value: 40000
            payment_address: zstabila1z8d5htmt6h26l5agk5nlxdlz66fahhcp8vwhyydrwfdajc5yalftew5uhwn6wjz4pwrxu0msu34
            rcm: 94afb02c6fd4b19ada89b6b85e2cc23f2fb76c5188ede646c5046b2539a3bf00
            memo: 2
        }
        tx_id: 19c8aaa244dbcdf30a4b2a02b9b17054dc5d8ebf41d1f82daea044e65dff29d5
    }
    {
        transparent_to_address: SV7ceN4tHDNPB47DMStcUFC3Y8QQ7KzN32
        transparent_amount: 130000
        tx_id: d45da3394be6c15220d31ac17c13e02130aab0c3edf97750620538f4efae366b
    }
]
```

### BackupShieldedSRC20Wallet

Back up one shielded address.

Example:

```console
> BackupShieldedSRC20Wallet
Please input your password for shieldedSRC20 wallet.
password:
The 1th shieldedSRC20 address is zstabila1mf0a0cy86j8rmn4l7dcdsnhyj2k46rem4qxwjqh4z0x26utlddtmmr5fk5dchzt2hpujyvgk69z
The 2th shieldedSRC20 address is zstabila1mnkdjl0802dqha9ufh4m80f2ua9cff2hct8geeh77llrz4ywgtu0ct8ygy6k5xavdkd278jyttj
The 3th shieldedSRC20 address is zstabila1z8d5htmt6h26l5agk5nlxdlz66fahhcp8vwhyydrwfdajc5yalftew5uhwn6wjz4pwrxu0msu34
Please choose between 1 and 3
1
sk:01ef2d71f8eef668e12db7aef1267c7d6a8f43c84dffa66fc09e2c749464190e
d :da5fd7e087d48e3dcebff3
BackupShieldedSRC20Wallet successful !!!
```

### ImportShieldedSRC20Wallet

Import one shielded address to local wallet.

Example:

```console
> ImportShieldedSRC20Wallet
ShieldedSRC20 wallet does not exist, will build it.
Please input password.
password:
Please input password again.
password:
Please input shieldedSRC20 wallet hex string. such as 'sk d',Max retry time:3
0eb458b309fa544066c40d80ce30a8002756c37d2716315c59a98c893dbb000a 11db4baf6bd5d5afd3a8b5
Import shieldedSRC20 wallet hex string is :
sk:0eb458b309fa544066c40d80ce30a8002756c37d2716315c59a98c893dbb000a
d :11db4baf6bd5d5afd3a8b5
Import new shieldedSRC20 wallet address is: zstabila1z8d5htmt6h26l5agk5nlxdlz66fahhcp8vwhyydrwfdajc5yalftew5uhwn6wjz4pwrxu0msu34
ImportShieldedSRC20Wallet successfully !!!
```

### ShowShieldedSRC20AddressInfo

```console
> ShowShieldedSRC20AddressInfo address
```
Display information about shielded addresses. If this address is not in the wallet, it will only display `d` and `pkd`

Example:

```console
> ListShieldedSRC20Address
ShieldedSRC20Address :
zstabila1mf0a0cy86j8rmn4l7dcdsnhyj2k46rem4qxwjqh4z0x26utlddtmmr5fk5dchzt2hpujyvgk69z
zstabila1mnkdjl0802dqha9ufh4m80f2ua9cff2hct8geeh77llrz4ywgtu0ct8ygy6k5xavdkd278jyttj
zstabila1z8d5htmt6h26l5agk5nlxdlz66fahhcp8vwhyydrwfdajc5yalftew5uhwn6wjz4pwrxu0msu34

> ShowShieldedSRC20AddressInfo zstabila1mf0a0cy86j8rmn4l7dcdsnhyj2k46rem4qxwjqh4z0x26utlddtmmr5fk5dchzt2hpujyvgk69z
The following variables are secret information, please don't show to other people!!!
sk :01ef2d71f8eef668e12db7aef1267c7d6a8f43c84dffa66fc09e2c749464190e
ivk:7d2e9c14ff1d82843f39cb69e8bcc228370e4ea8750669bba79e90c485d94c03
ovk:2c3d164fffa63b41a34f495e0c9d8af79d595cfb07db1539545ddcecf046d66e
pkd:70d84ee492ad5d0f3ba80ce902f513ccad717f6b57bd8e89b51b8b896ab87922
d  :da5fd7e087d48e3dcebff3

> ShowShieldedSRC20AddressInfo zstabila1z8d5htmt6h26l5agk8r7wxw9pyhc0a78hl5thva4k9kcn7fsqvygchyt3n2ncy0r4xv4j5mywnu
pkd:c7e719c5092f87f7c7bfe8bbb3b5b16d89f93003088c5c8b8cd53c11e3a99959
d  :11db4baf6bd5d5afd3a8b1
```


## How to use stabila-dex to sell asset

### MarketSellAsset

Create an order to sell asset    

> MarketSellAsset owner_address sell_token_id sell_token_quantity buy_token_id buy_token_quantity  

ownerAddress
> The address of the account that initiated the transaction

sell_token_id, sell_token_quantity
> ID and amount of the token want to sell

buy_token_id, buy_token_quantity
> ID and amount of the token want to buy

Example: 

```console
MarketSellAsset SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW  1000001 200 _ 100    

Get the result of the contract execution with the getTransactionInfoById command:   
getTransactionInfoById 10040f993cd9452b25bf367f38edadf11176355802baf61f3c49b96b4480d374   

{
	"id": "10040f993cd9452b25bf367f38edadf11176355802baf61f3c49b96b4480d374",
	"blockNumber": 669,
	"blockTimeStamp": 1578983493000,
	"contractResult": [
		""
	],
	"receipt": {
		"net_usage": 264
	}
} 
```

### GetMarketOrderByAccount

Get the order created by account(just include active status)

> GetMarketOrderByAccount ownerAddress

ownerAddress
> The address of the account that created market order

Example:

```console
GetMarketOrderByAccount SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW   
{
	"orders": [
		{
			"order_id": "fc9c64dfd48ae58952e85f05ecb8ec87f55e19402493bb2df501ae9d2da75db0",
			"owner_address": "SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW",
			"create_time": 1578983490000,
			"sell_token_id": "_",
			"sell_token_quantity": 100,
			"buy_token_id": "1000001",
			"buy_token_quantity": 200,
			"sell_token_quantity_remain": 100
		}
	]
}  
```

### GetMarketOrderById

Get the specific order by order_id

> GetMarketOrderById orderId

Example:  

```console
GetMarketOrderById fc9c64dfd48ae58952e85f05ecb8ec87f55e19402493bb2df501ae9d2da75db0   
{
	"order_id": "fc9c64dfd48ae58952e85f05ecb8ec87f55e19402493bb2df501ae9d2da75db0",
	"owner_address": "SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW",
	"create_time": 1578983490000,
	"sell_token_id": "_",
	"sell_token_quantity": 100,
	"buy_token_id": "1000001",
	"buy_token_quantity": 200,
}
```

### GetMarketPairList

Get market pair list

Example:

```console
GetMarketPairList   
{
	"orderPair": [
		{
			"sell_token_id": "_",
			"buy_token_id": "1000001"
		}
	]
}
```

### GetMarketOrderListByPair

Get order list by pair   

> GetMarketOrderListByPair sell_token_id buy_token_id   

sell_token_id
> ID of the token want to sell      

buy_token_id
> ID of the token want to buy

Example: 

```console
GetMarketOrderListByPair _ 1000001   
{
	"orders": [
		{
			"order_id": "fc9c64dfd48ae58952e85f05ecb8ec87f55e19402493bb2df501ae9d2da75db0",
			"owner_address": "SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW",
			"create_time": 1578983490000,
			"sell_token_id": "_",
			"sell_token_quantity": 100,
			"buy_token_id": "1000001",
			"buy_token_quantity": 200,
			"sell_token_quantity_remain": 100
		}
	]
}
```

### GetMarketPriceByPair

Get market price by pair   

> GetMarketPriceByPair sell_token_id buy_token_id   

sell_token_id
> ID of the token want to sell

buy_token_id
> ID of the token want to buy

Example:   

```console
GetMarketPriceByPair _ 1000001   
{
	"sell_token_id": "_",
	"buy_token_id": "1000001",
	"prices": [
		{
			"sell_token_quantity": 100,
			"buy_token_quantity": 200
		}
	]
}
```

### MarketCancelOrder

Cancel the order   

> MarketCancelOrder owner_address order_id  

owner_address
> the account address who have created the order

order_id
> the order id which want to cancel 

Example:   

```console
MarketCancelOrder SJCnKsPa7y5okkXvQAidZBzqx3QyQ6sxMW fc9c64dfd48ae58952e85f05ecb8ec87f55e19402493bb2df501ae9d2da75db0  
```

Get the result of the contract execution with the getTransactionInfoById command:  
```console
getTransactionInfoById b375787a098498623403c755b1399e82910385251b643811936d914c9f37bd27   
{
	"id": "b375787a098498623403c755b1399e82910385251b643811936d914c9f37bd27",
	"blockNumber": 1582,
	"blockTimeStamp": 1578986232000,
	"contractResult": [
		""
	],
	"receipt": {
		"net_usage": 283
	}
}
```
## Suggestions, Feedback & Issues
If you have a suggestion for improvement, feedback about a specific feature or any issue please contact us at info@stabilascan.org
