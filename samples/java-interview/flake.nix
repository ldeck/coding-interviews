{
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-25.05";
    utils.url = "github:numtide/flake-utils";
  };
  outputs = { self, nixpkgs, utils }: utils.lib.eachDefaultSystem (system:
    let
      pkgs = nixpkgs.legacyPackages.${system};
      ourJdk = pkgs.jdk21_headless;
    in
    {
      devShell = pkgs.mkShell {
        buildInputs = with pkgs; [
          coursier
          ourJdk
          (bloop.override { jre = ourJdk; })
          maven
        ];
      };
    }
  );
}
