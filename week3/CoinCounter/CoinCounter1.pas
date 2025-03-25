program CoinCounter;

uses
  SysUtils;

const
  VALID_DENOMINATIONS: array[1..6] of Integer = (1, 5, 10, 25, 50, 100);
  SENTINEL = 999;

var
  coin, totalCoins, totalValue: Integer;
  count: array[1..6] of Integer = (0, 0, 0, 0, 0, 0);
  value: array[1..6] of Integer = (0, 0, 0, 0, 0, 0);
  x, i, j: Integer;

function IsValidDenomination(c: Integer): Integer;
begin
  for i := 1 to x do
    if c = VALID_DENOMINATIONS[i] then
      Exit(i);
  Exit(0);
end;

begin
  x := 6;

  totalCoins := 0;
  totalValue := 0;

  if ParamCount = 0 then
  begin
    WriteLn('Please provide coin values as command-line arguments.');
    Halt(1);
  end;

  WriteLn('Processing coin values from arguments...');

  for j := 1 to ParamCount do
  begin
    coin := StrToIntDef(ParamStr(j), -1);
    if coin = SENTINEL then
      Break;

    i := IsValidDenomination(coin);
    if i <> 0 then
    begin
      Inc(count[i]);
      value[i] := value[i] + coin;
      Inc(totalCoins);
      totalValue := totalValue + coin;
    end
    else
      WriteLn('Invalid coin denomination: ', coin);
  end;

  WriteLn;
  WriteLn('Final Report:');
  WriteLn('-------------------------------------');
  WriteLn('Total Coins: ', totalCoins);
  WriteLn('Total Value: $', totalValue div 100, '.', totalValue mod 100:2);
  WriteLn('-------------------------------------');

  for i := 1 to x do
    if count[i] > 0 then
      WriteLn(VALID_DENOMINATIONS[i], ' cent(s): ', count[i], ' coin(s) totaling $', value[i] div 100, '.', value[i] mod 100:2);

  WriteLn('-------------------------------------');
  WriteLn('Grand Total: $', totalValue div 100, '.', totalValue mod 100:2);
end.