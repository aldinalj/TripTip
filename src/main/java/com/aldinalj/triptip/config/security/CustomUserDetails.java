package com.aldinalj.triptip.config.security;

import com.aldinalj.triptip.user.model.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

    public class CustomUserDetails implements UserDetails {

        private final String username;
        private final String password;
        private final Collection<? extends GrantedAuthority> authorities;
        private final boolean isAccountNonExpired;
        private final boolean isAccountNonLocked;
        private final boolean isCredentialsNonExpired;
        private final boolean isEnabled;

        public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
            this.username = username;
            this.password = password;
            this.authorities = authorities;
            this.isAccountNonExpired = isAccountNonExpired;
            this.isAccountNonLocked = isAccountNonLocked;
            this.isCredentialsNonExpired = isCredentialsNonExpired;
            this.isEnabled = isEnabled;
        }

        public CustomUserDetails(CustomUser customUser) {
            this.username = customUser.getUsername();
            this.password = customUser.getPassword();
            this.authorities = customUser.getAuthorities();
            this.isAccountNonExpired = customUser.isAccountNonExpired();
            this.isAccountNonLocked = customUser.isAccountNonLocked();
            this.isCredentialsNonExpired = customUser.isCredentialsNonExpired();
            this.isEnabled = customUser.isEnabled();
        }


        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public boolean isAccountNonExpired() {
            return isAccountNonExpired;
        }

        @Override
        public boolean isAccountNonLocked() {
            return isAccountNonLocked;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return isCredentialsNonExpired;
        }

        @Override
        public boolean isEnabled() {
            return isEnabled;
        }


    }


