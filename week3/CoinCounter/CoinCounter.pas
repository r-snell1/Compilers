program CoinCounter;

uses
  SysUtils;

const
  DENOMINATIONS: array[1..6] of Integer = (1, 5, 10, 25, 50, 100);
  COIN_NAMES: array[1..6] of String = ('Pennies', 'Nickels', 'Dimes', 'Quarters', 'Half Dollars', 'Dollars');

var
  count: array[1..6] of Integer;
  value: array[1..6] of Integer;
  totalCoins, totalValue: Integer;
  i: Integer;

begin
  totalCoins := 0;
  totalValue := 0;

  WriteLn('Enter the number of each type of coin:');
  
  for i := 1 to 6 do
  begin
    Write(COIN_NAMES[i], ': ');
    ReadLn(count[i]);  
    value[i] := count[i] * DENOMINATIONS[i];  
    totalCoins := totalCoins + count[i];  
    totalValue := totalValue + value[i];  
  end;

  WriteLn;
  WriteLn('Final Report:');
  WriteLn('-------------------------------------');
  WriteLn('Total Coins: ', totalCoins);
  WriteLn('Total Value: $', FormatFloat('0.00', totalValue / 100));
  WriteLn('-------------------------------------');

  for i := 1 to 6 do
    if count[i] > 0 then
      WriteLn(COIN_NAMES[i], ': ', count[i], ' coin(s) totaling $', FormatFloat('0.00', value[i] / 100));

  WriteLn('-------------------------------------');
  WriteLn('Grand Total: $', FormatFloat('0.00', totalValue / 100));
end.